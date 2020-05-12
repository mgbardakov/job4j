package ru.job4j.io.search;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FileSearchTest {
    List<String> expected;

     @Rule
     public TemporaryFolder folder = new TemporaryFolder();

     @Before
     public void doBefore() throws IOException {
         expected = new ArrayList<>();
         expected.add(folder.newFile("file.txt").getAbsolutePath());
         folder.newFolder("folder");
         expected.add(folder.newFile("folder/file.txt").getAbsolutePath());
     }
    @Test
    public void whenSearchedByNameThenTwoFiles() throws IOException {
         ArgsSearch args = new ArgsSearch(new String[] {"-d", folder.getRoot().getAbsolutePath(),
                                                        "-n", "file.txt", "-f", "-o", "log.txt"});
         List<String> result = FileSearch.search(args);
         assertThat(result, is(expected));
    }

    @Test
    public void whenSearchedByMaskThenTwoFiles() throws IOException {
        ArgsSearch args = new ArgsSearch(new String[] {"-d", folder.getRoot().getAbsolutePath(),
                                                       "-n", "*e.tx?", "-m", "-o", "log.txt"});
        List<String> result = FileSearch.search(args);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSearchedByRegexpThenTwoFiles() throws IOException {
        ArgsSearch args = new ArgsSearch(new String[] {"-d", folder.getRoot().getAbsolutePath(),
                                                       "-n", ".*.tx.", "-r", "-o", "log.txt"});
        List<String> result = FileSearch.search(args);
        assertThat(result, is(expected));
    }
}