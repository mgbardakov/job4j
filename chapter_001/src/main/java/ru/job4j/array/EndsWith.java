package ru.job4j.array;

/**
 * ArrayChar класс определения вадидности постфикса
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 12.01.2020
 * @version 1.0
 */
public class EndsWith {

    /**
     * Проверка валидности постфикса относительно строки
     * @param word - массив char из строки
     * @param post - массив char из постфикса
     * @return result - валиден/не валиден постфикс
     */
    public static boolean endsWith(char[] word, char[] post) {
        boolean result = true;
        for(int i = 0; i < post.length; i++) {
            if (post[post.length - i - 1] != word[word.length - i - 1]) {
                result = false;
                break;
            }
        }
        // проверить. что массив word имеет последние элементы одинаковые с post
        return result;
    }
}
