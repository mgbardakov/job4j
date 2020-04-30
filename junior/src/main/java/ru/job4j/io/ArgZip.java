package ru.job4j.io;

public class ArgZip {
    private final String[] args;
    private final int directory = 1;
    private final int exclude = 3;
    private final int output = 5;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public void valid() {
        if (args.length != 6) {
            throw new IllegalArgumentException("Arguments are invalid");
        }
    }

    public String directory() {
        return args[directory];
    }

    public String exclude() {
        return args[exclude].substring(2);
    }

    public String output() {
        return args[output];
    }
}