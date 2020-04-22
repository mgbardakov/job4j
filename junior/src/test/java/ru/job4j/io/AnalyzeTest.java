package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalyzeTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenTwoUnavailablePeriodsInTheMiddle() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01" + System.lineSeparator()
                    + System.lineSeparator()
                    + "500 10:57:01" + System.lineSeparator()
                    + System.lineSeparator()
                    + "400 10:58:01" + System.lineSeparator()
                    + System.lineSeparator()
                    + "200 10:59:01" + System.lineSeparator()
                    + System.lineSeparator()
                    + "500 11:01:02" + System.lineSeparator()
                    + System.lineSeparator()
                    + "200 11:02:02");
        }
        new Analyze().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(x -> rsl.append(x).append(System.lineSeparator()));
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01" + System.lineSeparator()
                                           + "11:01:02;11:02:02" + System.lineSeparator()));
        }

    @Test
    public void whenUnavailablePeriodInTheBeginning() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("500 10:56:01" + System.lineSeparator()
                    + System.lineSeparator()
                    + "500 10:57:01" + System.lineSeparator()
                    + System.lineSeparator()
                    + "400 10:58:01" + System.lineSeparator()
                    + System.lineSeparator()
                    + "200 10:59:01" + System.lineSeparator()
                    + System.lineSeparator()
                    + "300 11:01:02" + System.lineSeparator()
                    + System.lineSeparator()
                    + "200 11:02:02");
        }
        new Analyze().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(x -> rsl.append(x).append(System.lineSeparator()));
        }
        assertThat(rsl.toString(), is("10:59:01" + System.lineSeparator()));
    }

    @Test
    public void whenUnavailablePeriodInTheMiddleAndInTheEnd() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("300 10:56:01" + System.lineSeparator()
                    + System.lineSeparator()
                    + "500 10:57:01" + System.lineSeparator()
                    + System.lineSeparator()
                    + "400 10:58:01" + System.lineSeparator()
                    + System.lineSeparator()
                    + "200 10:59:01" + System.lineSeparator()
                    + System.lineSeparator()
                    + "500 11:01:02" + System.lineSeparator()
                    + System.lineSeparator()
                    + "400 11:02:02");
        }
        new Analyze().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(x -> rsl.append(x).append(System.lineSeparator()));
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01" + System.lineSeparator()
                                             + "11:01:02;" + System.lineSeparator()));
    }

}