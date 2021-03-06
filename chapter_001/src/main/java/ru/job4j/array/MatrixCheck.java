package ru.job4j.array;
/**
 * MatrixCheck класс для проверки однородности матрицы по Х
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 14.01.2020
 * @version 1.0
 */
public class MatrixCheck {
    /**
     * Проверка заполненности ряда матрицы по Х
     * @param board - матрица
     * @param row - ряд
     * @return result - заполнен/незаполнен ряд
     */
    public static boolean monoHorizontal(char[][] board, int row) {
        boolean result = true;
        for (int i = 0; i < board[row].length; i++) {
            if (board[row][i] != 'X') {
                result = false;
                break;
            }
        }
        return result;
    }
    /**
     * Проверка заполненности ряда матрицы по Х
     * @param board - матрица
     * @param column - столбец
     * @return boolean - заполнен/незаполнен столбец
     */
    public static boolean monoVertical(char[][] board, int column) {
        boolean result = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i][column] != 'X') {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Заполняет массив символами из дмагонали матрицы
     * @param board - матрица
     * @return - заполненный массив
     */
    public static char[] extractDiagonal(char[][] board) {
        char[] rsl = new char[board.length];
        for (int i = 0; i < board.length; i++) {
            rsl[i] = board[i][i];
        }
        return rsl;
    }

    /**
     * Проверяет наличие выигрышгых комбинацй в сокобан
     * @param board - матрица
     * @return boolean - есть комбинация / нет комбинации
     */
    public static boolean isWin(char[][] board) {
        boolean result = false;
        for (int i = 0; i < board.length; i++) {
            if (monoHorizontal(board, i) || monoVertical(board, i)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
