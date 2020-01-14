package ru.job4j.array;

/**
 * Slash решение задачи № 6.0
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 12.01.2020
 * @version 1.0
 */
public class Square {

    /**
     * Заполняет массив элементами от 1 до bound возведенными в квадрат
     * @param bound - размер массива
     * @return rst - заполненный массив
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int i = 1; i <= bound; i++) {
            rst[i - 1] = i * i;
        }
        return rst;
    }
}
