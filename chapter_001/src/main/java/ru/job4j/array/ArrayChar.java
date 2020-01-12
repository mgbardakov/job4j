package ru.job4j.array;

/**
 * ArrayChar класс определения вадидности префикса
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 12.01.2020
 * @version 1.0
 */
public class ArrayChar {

    /**
     * Проверка валидности префикса относительно строки
     * @param word - массив char из строки
     * @param pref - массив char из префикса
     * @return result - валиден/не валиден префикс
     */
    public static boolean startsWith(char[] word, char[] pref) {
        boolean result = false;
        for(int i = 0; i < pref.length; i++) {
            if (pref[i] == word[i]) result = true;
            else {
                result = false;
                break;
            }
        }
        return result;
    }
}
