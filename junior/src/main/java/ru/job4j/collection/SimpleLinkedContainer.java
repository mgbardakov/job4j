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
        return findNodeByIndex(index).value;
    }

    public void add(T model) {
        if (head == null) {
            head = new Node<>(model, null, null);
            tail = head;
        } else {
            tail.next = new Node<>(model, null, tail);
            tail = tail.next;
        }
        size++;
        modCount++;
    }

    public T delete(int index) {
        Node<T> removingNode = findNodeByIndex(index);
        if (removingNode.previous == null) {
            head = head.next;
        } else if (removingNode.next == null) {
            tail = tail.previous;
        } else {
            removingNode.previous.next = removingNode.next;
            removingNode.next.previous = removingNode.previous;
        }
        size--;
        return removingNode.value;
    }

    public int size() {
        return this.size;
    }

    private Node<T> findNodeByIndex(int index) {
        Objects.checkIndex(index, size);
        Iterator<Node<T>> it = index < size / 2 ? nodeIterator(true) : nodeIterator(false);
        int count = index < size / 2 ? index : size - index - 1;
        for (int i = 0; i < count; i++) {
            it.next();
        }
        return it.next();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final Iterator<Node<T>> nodeIterator = nodeIterator(true);

            @Override
            public boolean hasNext() {
                return nodeIterator.hasNext();
            }

            @Override
            public T next() {
                return nodeIterator.next().value;
            }
        };
    }

    /**
     * gets iterator for nodes
     * @param forward - true - forward iterator false - backwards iterator
     * @return iterator for nodes
     */
    private Iterator<Node<T>> nodeIterator(boolean forward) {
        return new Iterator<>() {
            private final int localModCount = modCount;
            private Node<T> current = forward ? head : tail;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Node<T> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements");
                }
                if (localModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                var rsl = current;
                current = forward ? current.next : current.previous;
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
