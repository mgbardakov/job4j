package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * soft-reference implementation of Cache interface
 * @author mbardakov
 * @since 25.07.2020
 */
public class SoftReferenceCache implements Cache {
    private Map<String, SoftReference<String>> cacheMap;
    private String cacheAddress;

    public SoftReferenceCache(String cacheAddress) {
        this.cacheMap = new HashMap<>();
        this.cacheAddress = cacheAddress;
    }

    @Override
    public String getFileText(String fileName) {
        String rsl;
        if (cacheMap.containsKey(fileName)) {
            rsl = cacheMap.get(fileName).get();
            if (rsl == null) {
                rsl = readTextFromFile(fileName);
                cacheMap.put(fileName, new SoftReference<>(rsl));
            }
        } else {
            rsl = readTextFromFile(fileName);
            cacheMap.put(fileName, new SoftReference<>(rsl));
        }
        return rsl;
    }

    /**
     * gets text from file
     * @param fileName - simple file name
     * @return text
     */
    private String readTextFromFile(String fileName) {
        var text = new StringBuilder();
        try (BufferedReader fileReader = new BufferedReader(
                new FileReader(cacheAddress + "/" + fileName)
        )) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                text.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }
}
