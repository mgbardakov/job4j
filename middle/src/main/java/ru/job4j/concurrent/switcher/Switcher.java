package ru.job4j.concurrent.switcher;

public class Switcher {
    private static final MasterSlaveBarrier BARRIER = new MasterSlaveBarrier();
    public static void main(String[] args) throws InterruptedException {

        Thread first = new Thread(
                () -> {
                    while (true) {
                        synchronized (BARRIER) {
                            BARRIER.tryMaster();
                            BARRIER.doneMaster();
                        }
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    while (true) {
                        synchronized (BARRIER) {
                            BARRIER.trySlave();
                            BARRIER.doneSlave();
                        }
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
