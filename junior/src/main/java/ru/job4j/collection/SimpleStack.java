package ru.job4j.collection;

/**
 * simple stack class
 * @param <T> - type of elements
 * @author mbardakov
 * @since 30.05.2020
 */
public class SimpleStack<T> {
    private SimpleLinkedContainer<T> linked = new SimpleLinkedContainer<>();

    /**
     * returns last element of stack and removes it
     * @return last element
     */
    public T pop() {
        return linked.delete(linked.size() - 1);
    }

    /**
     * adds new element to the end of the stack
     * @param value - new element
     */
    public void push(T value) {
        linked.add(value);
    }

    public int size() {
        return linked.size();
    }
}