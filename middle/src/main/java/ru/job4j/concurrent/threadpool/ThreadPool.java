package ru.job4j.concurrent.threadpool;

import ru.job4j.concurrent.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * simple thread pool
 * @author mbardakov
 * @since 25.10.2020
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        var size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            var thread = new Job(tasks);
            threads.add(thread);
            thread.start();
        }
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
