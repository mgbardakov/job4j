package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * simple dynamic array
 * @param <T> - type of element
 * @author mbardakov
 * @since 27.05.2020
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int modCount;
    private int size;

    public SimpleArray() {
        container = new Object[5];
        modCount = 0;
        size = 0;
    }
    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        if (size + 1 == container.length) {
            var newContainer = new Object[container.length * 2];
            System.arraycopy(container, 0, newContainer, 0, container.length);
            container = newContainer;
        }
        container[size++] = model;
        modCount++;

    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final int localModCount = modCount;
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                return pointer < size;
            }

            @Override @SuppressWarnings("unchecked")
            public T next() {
                if (localModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements");
                }
                return (T) container[pointer++];
            }
        };
    }
}