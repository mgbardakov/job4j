package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * forward linked element container
 * @param <T> - element type
 * @author mbardakov
 * @since 28.05.2020
 */
public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T rsl = head.value;
        head = head.next;
        return rsl;
    }

    /**
     * reverts links in the list
     */
    public void revert() {
        var firstNode = head;
        if (firstNode == null) {
            throw new UnsupportedOperationException("Empty list");
        }
        var secondNode = head.next;
        if (secondNode == null) {
            throw new UnsupportedOperationException("There's only one node in the list");
        }
        var thirdNode = secondNode.next;
        firstNode.next = null;
        secondNode.next = firstNode;

        while (thirdNode != null) {
            var nextNode = thirdNode.next;
            thirdNode.next = secondNode;
            thirdNode = nextNode;
        }
        head = secondNode;

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Iterator<Node<T>> nodeIterator = nodeIterator();

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

    private Iterator<Node<T>> nodeIterator() {
        return new Iterator<>() {
            Node<T> node = head;
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Node<T> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<T> value = node;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}