package ru.job4j.concurrent.switcher;

public class MasterSlaveBarrier {
    private static volatile boolean flag = true;
    public void tryMaster() {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Thread A");
    }

    public void trySlave() {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Thread B");
    }

    public void doneMaster() {
        flag = false;
        notifyAll();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doneSlave() {
        flag = true;
        notifyAll();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
