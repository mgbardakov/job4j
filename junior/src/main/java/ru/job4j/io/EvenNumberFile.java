package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("./junior/data/even.txt"))) {
            reader.lines().map(Integer::parseInt).forEach(x -> System.out.println(x % 2 != 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
