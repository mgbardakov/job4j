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
}
