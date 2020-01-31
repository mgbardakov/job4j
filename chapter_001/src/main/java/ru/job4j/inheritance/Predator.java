package ru.job4j.inheritance;

public class Predator extends Animal {
    public Predator() {
        System.out.println("Predator");
    }

    public Predator(String name) {
        super(name);
    }
}
