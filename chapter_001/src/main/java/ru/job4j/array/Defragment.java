package ru.job4j.array;

/**
 * Класс для дефрагментации массива
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 14.01.2020
 * @version 1.0
 */
public class Defragment {
    /**
     * Дефрагментация массива
     * @param array - массив
     * @return дефрагментированный массив
     */
    public static String[] compress(String[] array) {
        for (int index = 0; index < array.length; index++) {
            String cell = array[index];
            if (cell == null) {
                int count = index;
                while (array[count] == null && count < array.length - 1) {
                    count++;
                }
                array[index] = array[count];
                array[count] = null;
            }
            System.out.print(array[index] + " ");
        }
        return array;
    }

    public static void main(String[] args) {
        String[] input = {"I", null, "wanna", null, "be", null, "compressed"};
        String[] compressed = compress(input);
        System.out.println();
        for (int index = 0; index < compressed.length; index++) {
            System.out.print(compressed[index] + " ");
        }
    }
}