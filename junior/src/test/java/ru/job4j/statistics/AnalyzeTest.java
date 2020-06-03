package ru.job4j.statistics;

import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalyzeTest {
    @Test
    public void whenCurrentIsEmpty() {
        var previous = List.of(new Analyze.User(1, "Carlos"));
        var current = new ArrayList<Analyze.User>();
        var analyzer = new Analyze();
        var rsl = analyzer.diff(previous, current);
        assertThat(rsl, is(new Analyze.Info(0, 0, 1)));
    }

    @Test
    public void whenPreviousIsEmpty() {
        var previous = new ArrayList<Analyze.User>();
        var current = List.of(new Analyze.User(1, "Carlos"));
        var analyzer = new Analyze();
        var rsl = analyzer.diff(previous, current);
        assertThat(rsl, is(new Analyze.Info(1, 0, 0)));
    }

    @Test
    public void whenOneChanged() {
        var previous = List.of(new Analyze.User(1, "Carlos"));
        var current = List.of(new Analyze.User(1, "Charles"));
        var analyzer = new Analyze();
        var rsl = analyzer.diff(previous, current);
        assertThat(rsl, is(new Analyze.Info(0, 1, 0)));
    }

    @Test
    public void whenThreeAddedTwoDeletedAndOneChanged() {
        var previous = List.of(new Analyze.User(1, "Carlos"),
                               new Analyze.User(2, "Diego"),
                               new Analyze.User(3, "Peter"));
        var current = List.of(new Analyze.User(1, "Pablo"),
                              new Analyze.User(4, "Markus"),
                              new Analyze.User(5, "Lester"));
        var analyzer = new Analyze();
        var rsl = analyzer.diff(previous, current);
        assertThat(rsl, is(new Analyze.Info(2, 1, 2)));
    }


}