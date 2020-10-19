package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * count condition thread barrier
 * @author Bardakov
 * @since 19.10.2020
 */
@ThreadSafe
public class CountBarrier {

    private final int total;

    @GuardedBy("this")
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    /**
     * increments counter
     */
    public synchronized void count() {
        count++;
        notifyAll();
    }

    /**
     * awaits until counter reached total value
     */
    public synchronized void await() {
        while (count != total) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
