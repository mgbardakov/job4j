package ru.job4j.array;
/**
 * FindLoop класс для поиска элемента массива
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 12.01.2020
 * @version 1.0
 */
public class FindLoop {
    /**
     * Поиск индекса мереданного элемента
     * @param data - массив
     * @param el - искомый элемент
     * @return rst - индекс искомого элемента
     */
    public int indexOf(int[] data, int el) {
        int rst = -1; // если элемента нет в массиве, то возвращаем -1.
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    /**
     * Поиск индекса переданного элемента в указанном диапазоне
     * @param data - массив
     * @param el - искомый элемент
     * @param start - нижняя граница диапазон
     * @param finish - верхняя граница диапазоне
     * @return rst - индекс искомого элемента
     */
    public static int indexOf(int[] data, int el, int start, int finish) {
        int rst = -1;// если элемента нет в массиве, то возвращаем -1.
        for (int index = start; index < finish; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
