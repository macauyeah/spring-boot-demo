package macauyeah.personal.springdemo.lambda.FilterList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterList<E> extends ArrayList<E> {
    public FilterList(int initialCapacity) {
        super(initialCapacity);
    }

    public FilterList() {
        super();
    }

    public FilterList(Collection<? extends E> c) {
        super(c);
    }

    public FilterList<E> filter(Predicate<E> filterPredicate) {
        List<E> abstractRet = this.stream().filter(filterPredicate).collect(Collectors.toList());
        FilterList<E> ret = new FilterList<>(abstractRet);
        return ret;
    }
}
