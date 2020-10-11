package ru.job4j.concurrent;

/**
 * didactic class for thread state learning
 * @author mbardakov
 * @since 11.10.2020
 */
public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {}
        );
        Thread second = new Thread(
                () -> {}
        );
        printThreadNameAndState(first);
        printThreadNameAndState(second);
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED ||
                second.getState() != Thread.State.TERMINATED) {
            printThreadNameAndState(first);
            printThreadNameAndState(second);
        }
        printThreadNameAndState(first);
        printThreadNameAndState(second);
        System.out.println("process finished.");
    }

    private static void printThreadNameAndState(Thread thread) {
        System.out.println(String.format("%s : %s", thread.getName(),
                thread.getState()));
    }
}
