package ru.job4j.condition;
/**
 * Бот для демонстрации работы условных операторов
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 08.01.2020
 * @version 1.0
 */
public class DummyBot {
    /**
     * Возвращает строку с ответом, на переданную строку с вопросом
     * @param question - строка-вопрос
     * @return - строка-ответ
     */
    public static String answer(String question) {
        String rsl = "Это ставит меня в тупик. Задайте другой вопрос.";
        if ("Привет, Бот.".equals(question)) {
            rsl = "Привет, умник.";
        } else if ("Пока.".equals(question)) {
            rsl = "До скорой встречи.";
        }
            return rsl;
    }
}
