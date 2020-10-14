package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.io.*;
import java.util.function.Predicate;

@ThreadSafe
public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public String getContent() throws IOException {
        return getContentWithCondition(x -> true);
    }

    public String getContentWithoutUnicode() throws IOException {
        return getContentWithCondition(x -> x < 0x80);
    }

    public synchronized void saveContent(String content) throws IOException {
        OutputStream o = new FileOutputStream(file);
        for (int i = 0; i < content.length(); i += 1) {
            o.write(content.charAt(i));
        }
    }

    private synchronized String getContentWithCondition(
            Predicate<Integer> p) throws IOException {
        InputStream i = new FileInputStream(file);
        StringBuilder output = new StringBuilder();
        int data;
        while ((data = i.read()) != -1) {
            if (p.test(data)) {
                output.append((char) data);
            }
        }
        return output.toString();
    }
}