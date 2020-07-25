package ru.job4j.cache;

/**
 * interface for abstract cache
 * @author mbardakov
 * @since 25.07.2020
 */
public interface Cache {
    /**
     * gets file text by file name
     * @param fileName - short file name i. e. "File.txt"
     * @return fileText
     */
    String getFileText(String fileName);
}
