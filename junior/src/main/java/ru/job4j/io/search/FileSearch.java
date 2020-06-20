package ru.job4j.io.search;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * class for searching files with certain criteria
 * @author mbardakov
 * @since 10.05.2020
 */
public class FileSearch {
    public static void main(String[] args) throws IOException {
        ArgsSearch argsSearch = new ArgsSearch(args);
        argsSearch.valid();
        List<String> rslList;
        rslList = search(argsSearch);
        logFiles(rslList, argsSearch.directory(), argsSearch.output());
    }

    public static Predicate<Path> getCondition(ArgsSearch args) {
        Predicate<Path> rsl;
        switch (args.getDataType()) {
            case "-m" : Pattern maskPattern = getPattern(args.fileData());
                        rsl = x -> maskPattern.matcher(x.getFileName().toString()).matches();
                        break;
            case "-f" : rsl = x -> x.getFileName().toString().equals(args.fileData());
                        break;
            case "-r" : Pattern pattern = Pattern.compile(args.fileData());
                        rsl = x -> pattern.matcher(x.getFileName().toString()).matches();
                        break;
            default : throw new IllegalArgumentException("enter correct data type argument -m, -f or -r");
        }
        return rsl;
    }

    /**
     * searches directory by certain condition
     * @param args - arguments of the program
     * @return list of founded files
     * @throws IOException if there is no such directory
     */
    public static List<String> search(ArgsSearch args) throws IOException {
        var condition = getCondition(args);
        PathVisitor visitor = new PathVisitor(condition);
        Files.walkFileTree(Path.of(args.directory()), visitor);
        var result = visitor.getFiles();
        Collections.sort(result);
        return result;
    }

    /**
     *
     * @param list list with founded file paths
     * @param directory directory where to place the log file
     * @param output name of the log file
     */
    public static void logFiles(List<String> list, String directory, String output) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(directory + output))) {
            if (list != null) {
                list.forEach(writer::println);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * method converts mask string to regexp pattern
     * @param mask mask string
     * @return compiled pattern
     */
    public static Pattern getPattern(String mask) {
        Map<String, String> map = new HashMap<>();
        map.put("*", ".*");
        map.put(".", "\\.");
        map.put("?", ".");
        String regexp = mask.chars().mapToObj(x -> {
            if (map.containsKey((String.valueOf((char) x)))) {
                return map.get(String.valueOf((char) x));
            }  else {
                return String.valueOf((char) x);
            }
          }).reduce((x, y) -> x + y).orElse("");
        return Pattern.compile(regexp);
    }
}
