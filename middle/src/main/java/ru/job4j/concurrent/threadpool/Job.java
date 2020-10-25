package ru.job4j.concurrent.threadpool;

import ru.job4j.concurrent.SimpleBlockingQueue;

public class Job extends Thread {
    private final SimpleBlockingQueue<Runnable> tasks;

    public Job(SimpleBlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                tasks.poll().run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
