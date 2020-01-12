package ru.job4j.array;
/**
 * FindLoop класс переворачивания массива
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 12.01.2020
 * @version 1.0
 */
public class Turn {
    /**
     * Переворачивает массив
     * @param array - исходный массив
     * @return array - перевернутый массив
     */
    public int[] back(int[] array) {
        for (int i = 0; i < array.length - i; i++) {
            int tmp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = tmp;
        }
        return array;
    }
}