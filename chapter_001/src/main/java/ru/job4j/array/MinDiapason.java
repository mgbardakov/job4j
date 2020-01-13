package ru.job4j.array;

/**
 * MinDiapason класс для поиска минимального элемента массива в указанном диапазоне
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 13.01.2020
 * @version 1.0
 */
public class MinDiapason {

    /**
     * Поиск минимального значения в массиве
     * @param array - массив
     * @param start - нижняя граница диапазоне
     * @param finish - верхняя краница диапазона
     * @return min - минимальное значение
     */
    public static int findMin(int[] array, int start, int finish) {
        int min = array[start];
            for (int i = start; i <= finish; i++) {
                if(array[i] < min) min = array[i];
            }
        return min;
    }
}
