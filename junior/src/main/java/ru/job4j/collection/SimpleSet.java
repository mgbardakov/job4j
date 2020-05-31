package ru.job4j.collection;

import java.util.Iterator;

/**
 * simple realization of set
 * @param <E> - element type
 * @author mbardakov
 * @since 31.05.2020
 */
public class SimpleSet<E> implements Iterable<E> {
    private final SimpleArray<E> array = new SimpleArray<>();

    public void add(E value) {
        if (!contains(value)) {
            array.add(value);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }

    /**
     * checks if contains given element
     * @param value - element
     * @return contains/doesn't contain
     */
    private boolean contains(E value) {
        var rsl = false;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).equals(value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public String toString() {
        var rsl = new StringBuilder();
        for (E el : this) {
            rsl.append(el).append(" ");
        }
        return rsl.toString();
    }
}
