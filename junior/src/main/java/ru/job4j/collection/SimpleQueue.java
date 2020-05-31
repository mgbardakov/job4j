package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * simple queue on two stacks
 * @param <T> element type
 * @author mbardakov
 * @since 31.05.2020
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * returns first element and deletes it
     * @return first element
     */
    public T poll() {
        var stackSize = in.size();
        if (stackSize == 0) {
            throw new NoSuchElementException();
        }
        for (int i = 0; i < stackSize; i++) {
            out.push(in.pop());
        }
        var rsl = out.pop();
        for (int i = 0; i < stackSize - 1; i++) {
            in.push(out.pop());
        }
        return rsl;
    }

    /**
     * adds new element to the end of queue
     * @param value - element
     */
    public void push(T value) {
        in.push(value);
    }
}