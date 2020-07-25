package ru.job4j.cache;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SoftReferenceCacheTest {

    private String expected = new StringBuilder()
            .append("If you can keep your head when all about you")
            .append(System.lineSeparator())
            .append("Are losing theirs and blaming it on you,")
            .append(System.lineSeparator())
            .append("If you can trust yourself when all men doubt you,")
            .append(System.lineSeparator())
            .append("But make allowance for their doubting too;")
            .append(System.lineSeparator()).toString();

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void doBefore() throws IOException {
        File file = folder.newFile("Kipling.txt");
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(expected);
        } catch (Exception ignore) {
        }
    }

    @Test
    public void whenGetFileTextByFileName() {
        Cache cache = new SoftReferenceCache(folder.getRoot().getAbsolutePath());
        assertThat(cache.getFileText("Kipling.txt"), is(expected));
    }
}