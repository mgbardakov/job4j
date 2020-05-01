package ru.job4j.io.chat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ChatTest {
    File sourceFile;
    File logFile;
    File consoleFile;
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void doBefore() throws IOException {
        sourceFile = folder.newFile("source.txt");
        logFile = folder.newFile("log.txt");
        consoleFile = folder.newFile("console.txt");
    }

    @Test
    public void whenChatEnds() {
        try (PrintStream log = new PrintStream(new FileOutputStream(logFile));
             PrintStream console = new PrintStream(new FileOutputStream(consoleFile))) {
             Bot bot = new Bot(sourceFile.getPath());
             var rsl = Chat.handleLine(bot, "закончить", log::println, console::println);
             assertThat(rsl, is(false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenBotStops() {
        try (PrintStream log = new PrintStream(new FileOutputStream(logFile));
             PrintStream console = new PrintStream(new FileOutputStream(consoleFile))) {
             Bot bot = new Bot(sourceFile.getPath());
             Chat.handleLine(bot, "стоп", log::println, console::println);
             assertThat(bot.isActive(), is(false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenBotContinues() {
        try (PrintStream log = new PrintStream(new FileOutputStream(logFile));
             PrintStream console = new PrintStream(new FileOutputStream(consoleFile))) {
            Bot bot = new Bot(sourceFile.getPath());
            bot.setActive(false);
            Chat.handleLine(bot, "продолжить", log::println, console::println);
            assertThat(bot.isActive(), is(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenBotAnswers() {
        try (PrintStream printSource = new PrintStream(sourceFile)) {
            printSource.println("Hi there!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintStream log = new PrintStream(new FileOutputStream(logFile));
             PrintStream console = new PrintStream(new FileOutputStream(consoleFile))) {
            Bot bot = new Bot(sourceFile.getPath());
            Chat.handleLine(bot, "whatever", log::println, console::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Scanner logScanner = new Scanner(logFile);
             Scanner consoleScanner = new Scanner(consoleFile)) {
             String inputLogLine = logScanner.nextLine();
             String botLogLine = logScanner.nextLine();
             String botConsoleLine = consoleScanner.nextLine();
             assertThat(inputLogLine, is("whatever"));
             assertThat(botLogLine, is("Hi there!"));
             assertThat(botConsoleLine, is("Hi there!"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}