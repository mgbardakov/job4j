package ru.job4j.loop;
/**
 * Класс для отображения шахматной доски в псевдографике
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 09.01.2020
 * @version 1.0
 */
public class Board {
    /**
     * Метод рисует доску
     * @param width - ширина доски
     * @param height - высота доски
     */
    public static void paint(int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((j + i) % 2 == 0) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        paint(3, 3);
        System.out.println();
        paint(4, 4);
    }
}
