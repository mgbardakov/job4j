package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw  new IllegalStateException("Root folder and/or extension are null");
        }
        Path start = Paths.get(args[0]);
        List<String> filesToShow = search(start, args[1]);
        filesToShow.forEach(System.out::println);
    }
    public static List<String> search(Path root, String ext) throws IOException {
        FileSearcher visitor = new FileSearcher(x -> x.endsWith(String.format(".%s", ext)));
        Files.walkFileTree(root, visitor);
        return visitor.getFiles();
    }
}