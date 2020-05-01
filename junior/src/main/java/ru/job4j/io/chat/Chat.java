package ru.job4j.io.chat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * class with main method of console chat
 * @author mbardakov
 * @since 01.05.2020
 */
public class Chat {
    private static final String END = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("invalid file path, add path as argument");
        }
        Bot bot = new Bot(args[0]);
        try (Scanner consoleReader = new Scanner(System.in);
            PrintStream log = new PrintStream(new FileOutputStream("./junior/data/log.txt"))) {
            var run = true;
            while (run) {
                var line = consoleReader.nextLine();
                run = handleLine(bot, line, log::println, System.out::println);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean handleLine(Bot bot, String line, Consumer<String> log, Consumer<String> console) {
        var rsl = true;
        log.accept(line);
        if (END.equals(line)) {
            rsl = false;
        } else if (STOP.equals(line)) {
            bot.setActive(false);
        } else if (CONTINUE.equals(line)) {
            bot.setActive(true);
        } else if (bot.isActive()) {
            var message = bot.getRandomLine();
            log.accept(message);
            console.accept(message);
        }
        return rsl;
    }
}
