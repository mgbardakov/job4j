package ru.job4j.concurrent;

public class ConsoleProgress implements  Runnable {
    @Override
    public void run() {
        int count = 0;
        var process = new char[]{'/', '|', '\\'};
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\r load: " + process[count++]);
                if (count == process.length) {
                    count = 0;
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
            Thread progress = new Thread(new ConsoleProgress());
            progress.start();
            Thread.sleep(5000);
            progress.interrupt();
    }
}
