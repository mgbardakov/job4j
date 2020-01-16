package ru.job4j.sort;

import java.util.Arrays;
/**
 * Класс для упорядоченного объединения массивов
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 16.01.2020
 * @version 1.0
 */
public class Merge {

    /**
     * Объедтняет
     * @param left - первый массив
     * @param right - второй массив
     * @return объединенный массив
     */
    public int[] merge(int[] left, int[] right) {
        int[] rsl = new int[left.length + right.length];
        int i = 0, j = 0;
        while (i + j < rsl.length) {
            if (j == right.length || i != left.length
                    && left[i] < right[j]) {
                rsl[i + j] = left[i++];
            } else {
                rsl[i + j] = right[j++];
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        Merge process = new Merge();
        int[] rsl = process.merge(
                new int[] {1, 3, 5, 6, 7},
                new int[] {2, 4}
        );
        System.out.println(Arrays.toString(rsl));
    }
}
