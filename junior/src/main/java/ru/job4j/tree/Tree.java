package ru.job4j.tree;

import java.util.*;

/**
 * realization of SimpleTree interface
 * @param <E> - element type
 * @author mbardakov
 * @since 03.06.2020
 */
class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        var parentNode = findBy(parent);
        if (parentNode.isPresent()) {
            parentNode.get().children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    /**
     * check if tree is binary
     * @return binary/non binary
     */
    public boolean isBinary() {
        boolean rsl = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty() && rsl) {
            Node<E> el = data.poll();
            if (el.children.size() > 2) {
                rsl = false;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

}