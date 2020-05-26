package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Basic implementation of Store interface
 * @param <T> - type of stored objects
 * @author mbardakov
 * @since 26.05.2020
 */
public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        var rsl = false;
        var index = findIndexByCondition(x -> x.getId().equals(id));
        if (index != -1) {
            mem.set(index, model);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        var rsl = false;
        var index = findIndexByCondition(x -> x.getId().equals(id));
        if (index != -1) {
            mem.remove(index);
            rsl = true;
        }
        return  rsl;
    }

    @Override
    public T findById(String id) {
        T rsl = null;
        var index = findIndexByCondition(x -> x.getId().equals(id));
        if (index != -1) {
            rsl = mem.get(index);
        }
        return rsl;
    }

    /**
     * finds index of an object by certain condition
     * @param condition - predicate
     * @return index of an element if success / -1 if failed
     */
    private int findIndexByCondition(Predicate<T> condition) {
        var rsl = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (condition.test(mem.get(i))) {
                rsl = i;
            }
        }
        return rsl;
    }
}