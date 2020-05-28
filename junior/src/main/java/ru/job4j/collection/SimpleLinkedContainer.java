package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * simple linked container
 * @param <T> - type of elements
 * @author mbardakov
 * @since 28.05.2020
 */
public class SimpleLinkedContainer<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int modCount;
    private int size;

    public SimpleLinkedContainer() {
        modCount = 0;
        size = 0;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Iterator<T> it = index < size / 2 ? iterator() : backwardsIterator();
        int count = index < size / 2 ? index : size - index - 1;
        for (int i = 0; i < count; i++) {
            it.next();
        }
        return it.next();
    }

    public void add(T model) {
        if (head == null) {
            head = new Node<>(model, null, null);
            tail = head;
        } else {
            tail = new Node<>(model, null, tail);
        }
        size++;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final int localModCount = modCount;
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements");
                }
                if (localModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                var rsl = current.value;
                current = current.next;
                return rsl;
            }
        };
    }

    public Iterator<T> backwardsIterator() {
        return new Iterator<>() {
            private final int localModCount = modCount;
            private Node<T> current = tail;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (localModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements");
                }
                var rsl = current.value;
                current = current.previous;
                return rsl;
            }
        };
    }
    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> previous;

        public Node(T value, Node<T> next, Node<T> previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }
}
