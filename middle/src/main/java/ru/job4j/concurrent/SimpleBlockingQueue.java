package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * bounded blocking queue class
 * @param <T> element type
 * @author Bardakov
 * @since 21.10.2020
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final int bound;

    public SimpleBlockingQueue(int bound) {
        this.bound = bound;
    }

    public SimpleBlockingQueue() {
        this.bound = 3;
    }

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) {
        while(queue.size() == bound) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        queue.offer(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while(queue.isEmpty()) {
                wait();
       }
        var rsl = queue.poll();
        notifyAll();
        return rsl;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}