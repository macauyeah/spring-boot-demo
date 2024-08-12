package io.github.macauyeah.jpaspecification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

public class DynamicSpecification {
    private static final String combineByAnd = "combineByAnd";
    private static final String combineByOr = "combineByOr";

    public static <E> Specification<E> searchWithAnd(Class<E> entityClass, SearchSchema searchSchema) {
        return (root, query, builder) -> {
            if (query != null) {
                query.distinct(true);
            }
            return searchJoin(root, builder, searchSchema, combineByAnd);
        };
    }

    public static <E> Specification<E> searchWithOr(Class<E> entityClass, SearchSchema searchSchema) {
        return (root, query, builder) -> {
            if (query != null) {
                query.distinct(true);
            }
            return searchJoin(root, builder, searchSchema, combineByOr);
        };
    }

    private static Predicate searchJoin(
            From<?, ?> joinObject,
            CriteriaBuilder builder,
            SearchSchema searchSchema,
            String combineMode) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.addAll(
                searchPath(joinObject, builder, searchSchema));

        List<Predicate> joinPredicates = searchSchema.getJoinValues().entrySet().stream().map(
                (mapEntry) -> {
                    Join<Object, Object> joinResult = joinObject.join(mapEntry.getKey());
                    return searchJoin(joinResult, builder, mapEntry.getValue(), combineMode);
                }).collect(Collectors.toList());
        predicates.addAll(joinPredicates);

        if (combineByAnd.equals(combineMode)) {
            return combinePredicatesWithAndOperator(builder, predicates);
        } else if (combineByOr.equals(combineMode)) {
            return combinePredicatesWithOrOperator(builder, predicates);
        } else {
            return null;
        }
    }

    private static List<Predicate> searchPath(
            Path<?> path,
            CriteriaBuilder builder,
            SearchSchema searchSchema) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.addAll(
                getStringPredicates(
                        searchSchema,
                        path,
                        builder));

        predicates.addAll(
                getDatePredicates(
                        searchSchema,
                        path,
                        builder));
        return predicates;
    }

    private static List<Predicate> getStringPredicates(
            SearchSchema searchSchema,
            Path<?> path,
            CriteriaBuilder builder) {
        List<Predicate> predicates = searchSchema.getStringValues().entrySet().stream().map(
                (mapEntry) -> {
                    return generateEqualPredicate(
                            path,
                            builder,
                            mapEntry.getKey(),
                            mapEntry.getValue());
                }).collect(Collectors.toList());

        predicates.addAll(searchSchema.getSubStringValues().entrySet().stream().map(
                (mapEntry) -> {
                    return generateStringLikePredicate(
                            path,
                            builder,
                            mapEntry.getKey(),
                            mapEntry.getValue());
                }).collect(Collectors.toList()));
        return predicates;
    }

    private static Predicate generateStringLikePredicate(
            Path<?> path, CriteriaBuilder cb, String key, String value) {
        return cb.like(path.get(key), "%" + value + "%");
    }

    private static List<Predicate> getDatePredicates(
            SearchSchema searchSchema,
            Path<?> path,
            CriteriaBuilder builder) {
        List<Predicate> predicates = searchSchema.getDateValues().entrySet().stream().map(
                (mapEntry) -> {
                    return generateEqualPredicate(
                            path,
                            builder,
                            mapEntry.getKey(),
                            mapEntry.getValue());
                }).collect(Collectors.toList());
        predicates.addAll(searchSchema.getDateGreaterThan().entrySet().stream().map(
                (mapEntry) -> {
                    return generateGreaterThanPredicate(
                            path,
                            builder,
                            mapEntry.getKey(),
                            mapEntry.getValue());
                }).collect(Collectors.toList()));
        predicates.addAll(searchSchema.getDateLessThan().entrySet().stream().map(
                (mapEntry) -> {
                    return generateLessThanPredicate(
                            path,
                            builder,
                            mapEntry.getKey(),
                            mapEntry.getValue());
                }).collect(Collectors.toList()));
        predicates.addAll(searchSchema.getDateBetween().entrySet().stream().map(
                (mapEntry) -> {
                    return generateBetweenPredicate(
                            path,
                            builder,
                            mapEntry.getKey(),
                            mapEntry.getValue().getLowerBound(),
                            mapEntry.getValue().getUpperBound());
                }).collect(Collectors.toList()));
        return predicates;
    }

    private static <Y> Predicate generateEqualPredicate(
            Path<?> path, CriteriaBuilder cb, String key, Y value) {
        return cb.equal(path.<Y>get(key), value);
    }

    private static <Y extends Comparable<? super Y>> Predicate generateGreaterThanPredicate(
            Path<?> path, CriteriaBuilder cb, String key, Y value) {
        return cb.greaterThan(path.<Y>get(key), value);
    }

    private static <Y extends Comparable<? super Y>> Predicate generateLessThanPredicate(
            Path<?> path, CriteriaBuilder cb, String key, Y value) {
        return cb.lessThan(path.<Y>get(key), value);
    }

    private static <Y extends Comparable<? super Y>> Predicate generateBetweenPredicate(
            Path<?> path, CriteriaBuilder cb, String key, Y lower, Y upper) {
        return cb.between(path.<Y>get(key), lower, upper);
    }

    private static Predicate combinePredicatesWithAndOperator(CriteriaBuilder builder,
            List<Predicate> predicates) {
        return builder.and(predicates.toArray(new Predicate[0]));
    }

    private static Predicate combinePredicatesWithOrOperator(CriteriaBuilder builder,
            List<Predicate> predicates) {
        return builder.or(predicates.toArray(new Predicate[0]));
    }
}
