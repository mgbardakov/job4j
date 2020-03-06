package ru.job4j.kiss;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;


public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return maxOrMin((o1, o2) -> comparator.compare(o1, o2) > 0, value);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return maxOrMin((o1, o2) -> comparator.compare(o1, o2) < 0, value);
    }
    private <T> T maxOrMin(BiPredicate<T, T> bip, List<T> value) {
        Iterator<T> iterator = value.iterator();
        T candidate = iterator.next();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (bip.test(next, candidate)) {
                candidate = next;
            }
        }
        return candidate;
    }
}