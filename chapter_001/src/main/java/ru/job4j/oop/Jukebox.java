package ru.job4j.oop;
/**
 * Jukebox класс выводящий в консоль тексты песен
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 17.01.2020
 * @version 1.0
 */
public class Jukebox {
    /**
     * В зависимоти от переданного значения выводит в консоль тот или иной текст
     * @param position - значение типа int
     */
    public void music(int position) {
        String gena =                      "Пусть бегут неуклюже"
                + System.lineSeparator() + "Пешеходы по лужам,"
                + System.lineSeparator() + "А вода по асфальту рекой."
                + System.lineSeparator() + "И неясно прохожим,"
                + System.lineSeparator() + "В этот день непогожий,"
                + System.lineSeparator() + "Почему я весёлый такой.";
        String hrusha =                    "Спят усталые игрушки, книжки спят."
                + System.lineSeparator() + "Одеяла и подушки ждут ребят."
                + System.lineSeparator() + "Даже сказка спать ложится,"
                + System.lineSeparator() + "Чтобы ночью нам присниться."
                + System.lineSeparator() + "Ты ей пожелай:"
                + System.lineSeparator() +   "Баю-бай.";
        String unknown = "Песня не найдена";
            if (position == 1) {
            System.out.println(gena);
            System.out.println();
            } else if (position == 2) {
            System.out.println(hrusha);
            System.out.println();
            } else {
            System.out.println(unknown);
            System.out.println();
            }

        }

    public static void main(String[] args) {
        Jukebox singer = new Jukebox();
        singer.music(1);
        singer.music(2);
        singer.music(0);
    }
}
