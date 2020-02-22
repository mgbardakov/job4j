package ru.job4j.tracker;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;
public class SortItemUpTest {
    @Test
    public void sortUp() {
        List<Item> result = Arrays.asList(
                new Item("Bread"),
                new Item("Apple"),
                new Item("Delta"),
                new Item("Coal")
        );
        result.sort(new SortItemUp());
        List<Item> expected = Arrays.asList(
                new Item("Apple"),
                new Item("Bread"),
                new Item("Coal"),
                new Item("Delta")
        );
        assertThat(result, is(expected));
        }


}

