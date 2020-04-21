package ru.job4j.io;

import java.io.*;
import java.util.regex.Pattern;


public class Analyze {
    public void unavailable(String source, String target) {
        Pattern pattern = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
            PrintStream filePrinter = new PrintStream(new FileOutputStream(target))) {
            String previous = reader.readLine();
            String current;
            while (reader.ready()) {
                current = reader.readLine();
                if (isAvailable(previous) && !isAvailable(current)) {
                    filePrinter.print(String.format("%s;", pattern.split(current)[1]));
                } else if (!isAvailable(previous) && isAvailable(current)) {
                    filePrinter.print(pattern.split(current)[1] + System.lineSeparator());
                }
                previous = current;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Analyze().unavailable("./junior/data/server.log", "./junior/data/unavailable.csv");
    }

    private boolean isAvailable(String line) {
        return !(line.startsWith("500") || line.startsWith("400"));
    }

}

