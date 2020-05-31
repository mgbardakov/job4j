package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {
    @Test
    public void whenAddedOneTwoThreeThenOneTwoThree() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        assertThat(set.toString(), is("one two three "));
    }

    @Test
    public void whenAddedOneTwoTwoThreeThenOneTwoThree() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("one");
        set.add("two");
        set.add("two");
        set.add("three");
        assertThat(set.toString(), is("one two three "));
    }

}