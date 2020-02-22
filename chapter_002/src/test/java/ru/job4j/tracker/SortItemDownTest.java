package ru.job4j.tracker;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;
public class SortItemDownTest {
    @Test
    public void sortDown() {
        List<Item> result = Arrays.asList(
                new Item("Bread"),
                new Item("Apple"),
                new Item("Delta"),
                new Item("Coal")
        );
        result.sort(new SortItemDown());
        List<Item> expected = Arrays.asList(
                new Item("Delta"),
                new Item("Coal"),
                new Item("Bread"),
                new Item("Apple")
        );
        assertThat(result, is(expected));
    }


}