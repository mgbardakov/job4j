package ru.job4j.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

/**
 * forkjoin search in element array
 * @param <T> - element type
 * @author mbardakov
 * @since 29.10.2020
 */
public class ForkJoinSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final Predicate<T> condition;
    private final int from;
    private final int to;

    public ForkJoinSearch(T[] array, int from, int to, Predicate<T> pred) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.condition = pred;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            for (int i = 0; i < array.length; i++) {
                if (condition.test(array[i])) {
                    return i;
                }
            }
            return -1;
        }
        int mid = (from + to) / 2;
        ForkJoinSearch<T> leftSearch = new ForkJoinSearch<>(array, from, mid, condition);
        ForkJoinSearch<T> rightSearch = new ForkJoinSearch<>(array, mid + 1, to, condition);
        leftSearch.fork();
        rightSearch.fork();
        Integer left = leftSearch.join();
        Integer right = rightSearch.join();
        return left > right ? left : right;
    }

    public static <V> int search(V[] array, Predicate<V> pred) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ForkJoinSearch<>(
                array, 0, array.length - 1, pred));
    }
}
