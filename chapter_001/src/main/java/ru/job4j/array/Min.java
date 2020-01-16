package ru.job4j.array;

/**
 * Min класс для поиска минимального элемента массива
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 13.01.2020
 * @version 1.0
 */
public class Min {

    /**
     * Поиск минимального значения в массиве
     * @param array - массив
     * @return min - минимальное значение
     */
    public static int findMin(int[] array) {
        int min = array[0];
        for (int index = 0; index < array.length; index++) {
            if (array[index] < min) {
                min = array[index];
            }
        }
        return min;
    }
}
