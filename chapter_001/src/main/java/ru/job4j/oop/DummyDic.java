package ru.job4j.oop;
/**
 * DummyDic класс для демонстрации работы методов с возвращаемым значением
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 19.01.2020
 * @version 1.0
 */
public class DummyDic {

    /**
     * В зависимоти от переданного значения выводит в консоль тот или иной текст
     * @param eng - строка для конкатенации
     * @return - результирующая строка
     */
    public String engToRus(String eng) {
        return "Неизвестное слово. " + eng;
    }
}
