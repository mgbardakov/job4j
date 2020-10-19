package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.collection.SimpleArray;

import java.util.Iterator;

/**
 * threadsafe list
 * @param <T> element type
 * @author mbardakov
 * @since 19.10.2020
 */
@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    private final SimpleArray<T> array;

    public SingleLockList() {
        this.array = new SimpleArray<>();
    }

    public synchronized void add(T value) {
        array.add(value);
    }

    public synchronized T get(int index) {
        return array.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.array).iterator();
    }
    private synchronized SimpleArray<T> copy(SimpleArray<T> array) {
        var newArray = new SimpleArray<T>();
        for (T t : array) {
            newArray.add(t);
        }
        return newArray;
    }
}