package ru.job4j.array;
/**
 * Таблица умножения
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 13.01.2020
 * @version 1.0
 */
public class Matrix {

    /**
     * Заполнение матрицы таблицей умножения
     * @param size - размер матрицы
     * @return table - заполненная матрица
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}
