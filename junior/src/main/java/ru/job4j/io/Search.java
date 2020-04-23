package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get("./junior/data");
        List<String> filesToShow = search(start, "properties");
        filesToShow.forEach(System.out::println);
    }
    public static List<String> search(Path root, String ext) throws IOException {
        FileSearcher visitor = new FileSearcher(x -> x.endsWith(String.format(".%s", ext)));
        Files.walkFileTree(root, visitor);
        return visitor.getFiles();
    }
}