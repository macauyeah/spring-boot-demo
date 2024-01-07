package macauyeah.personal.springdemo.jpaspecification;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class DynamicSpecification {
    public static <E> Specification<E> search(SearchSchema<E> searchSchema) {
        return (root, query, builder) -> {
            query.distinct(true);
            return searchPath(root, builder, searchSchema);
        };
    }

    public static <E> Predicate searchPath(
            Path<E> path,
            CriteriaBuilder builder,
            SearchSchema<E> searchSchema) {
        
            Predicate init = builder.and();
            List<Predicate> strPredicates = getStringPredicates(
                    searchSchema,
                    path,
                    builder);

            for (Predicate strPredicate : strPredicates) {
                init = builder.and(init, strPredicate);
            }

            searchSchema.getJoinValues().entrySet().stream().map(
                    (mapEntry) -> {
                        Join<?, E> joinResult = path.join(mapEntry.getKey());
                        Path<?> nextPath = joinResult.get("somethinga");
                        return "NOT IMPLEMENT";
                    });
            return init;
    }

    private static <E> List<Predicate> getStringPredicates(
            SearchSchema<E> searchSchema,
            Path<E> path,
            CriteriaBuilder builder) {
        List<Predicate> strPredicates = searchSchema.getStringValues().entrySet().stream().map(
                (mapEntry) -> {
                    return generateStringPredicate(
                            path,
                            builder,
                            mapEntry.getKey(),
                            mapEntry.getValue());
                }).collect(Collectors.toList());
        return strPredicates;
    }

    private static <E> Predicate generateStringPredicate(
            Path<E> path, CriteriaBuilder cb, String key, String value) {
        return cb.equal(path.get(key), value);
    }
}
