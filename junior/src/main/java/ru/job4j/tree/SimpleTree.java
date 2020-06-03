package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * interface for simple tree
 * @param <E> - element type
 * @author mbardakov
 * @since 03.06.2020
 */
public interface SimpleTree<E> {
    /**
     * adds new element to the tree
     * @param parent - parent element
     * @param child - child element
     * @return success/failure
     */
    boolean add(E parent, E child);

    /**
     * finds node by element value
     * @param value - element value
     * @return node
     */
    Optional<Node<E>> findBy(E value);

    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }
    }
}