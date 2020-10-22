package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * non-clocking CAS counter
 * @author mbardakov
 * @since  22.10.2020
 */
@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    /**
     * increments count value
     */
    public void increment() {
        Integer temp;
        do {
            temp = count.get();
        }
        while (!count.compareAndSet(temp, temp + 1));
    }

    /**
     * gets current count value
     * @return current count value
     */
    public int get() {
        return count.get();
    }
}