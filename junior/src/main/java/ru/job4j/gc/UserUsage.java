package ru.job4j.gc;

public class UserUsage {
    public static void main(String[] args) {
        for (int i = 0; i < 4000; i++) {
            new User(1, 2);
        }
    }
}
