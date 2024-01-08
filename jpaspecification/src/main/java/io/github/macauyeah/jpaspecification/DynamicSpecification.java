package io.github.macauyeah.jpaspecification;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

public class DynamicSpecification {
    public static <E> Specification<E> search(Class<E> entityClass, SearchSchema searchSchema) {
        return (root, query, builder) -> {
            query.distinct(true);
            return searchJoin(root, builder, searchSchema);
        };
    }

    private static Predicate searchJoin(
            From<?, ?> joinObject,
            CriteriaBuilder builder,
            SearchSchema searchSchema) {

        Predicate ret = searchPath(joinObject, builder, searchSchema);

        List<Predicate> joinPredicates = searchSchema.getJoinValues().entrySet().stream().map(
                (mapEntry) -> {
                    Join<Object, Object> joinResult = joinObject.join(mapEntry.getKey());
                    return searchJoin(joinResult, builder, mapEntry.getValue());
                }).collect(Collectors.toList());

        ret = combinePredicatesWithAndOperator(builder, ret, joinPredicates);
        return ret;
    }

    private static Predicate searchPath(
            Path<?> path,
            CriteriaBuilder builder,
            SearchSchema searchSchema) {

        Predicate ret = builder.and();
        List<Predicate> strPredicates = getStringPredicates(
                searchSchema,
                path,
                builder);

        ret = combinePredicatesWithAndOperator(builder, ret, strPredicates);
        ret = combinePredicatesWithAndOperator(builder, ret, getDatePredicates(searchSchema, path, builder));

        return ret;
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

    private static Predicate combinePredicatesWithAndOperator(CriteriaBuilder builder,
            Predicate head,
            List<Predicate> predicates) {
        Predicate ret = head;
        for (Predicate joinPredicate : predicates) {
            ret = builder.and(ret, joinPredicate);
        }
        return ret;
    }
}
