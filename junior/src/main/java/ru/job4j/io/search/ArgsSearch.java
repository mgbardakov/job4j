package ru.job4j.io.search;

/**
 * class for managing arguments
 * @author mbardakov
 * @since 10.05.2020
 */
public class ArgsSearch {
    private final String[] args;
    private static final int DIR = 1;
    private static final int NAME = 3;
    private static final int TYPE = 4;
    private static final int OUTPUT = 6;

    public ArgsSearch(String[] args) {
        this.args = args;
    }

    public void valid() {
        if (args.length != 7) {
            throw new IllegalArgumentException("Arguments are invalid Usage: java -jar find.jar -d c:/ -n *.txt -m -o log.txt");
        }
    }

    public String directory() {
        return args[DIR];
    }

    public String fileData() {
        return args[NAME];
    }

    public String getDataType() {
        return args[TYPE];
    }

    public String output() {
        return args[OUTPUT];
    }
}
