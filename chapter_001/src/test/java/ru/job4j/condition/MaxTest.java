package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {
    @Test
    public void whenMax1To2Then2() {
        int result = Max.max(1, 2);
        assertThat(result, is(2));
    }
    @Test
    public void whenMax2To1Then1() {
        int result = Max.max(2, 1);
        assertThat(result, is(2));
    }
    @Test
    public void whenEqualsThenAny() {
        int result = Max.max(3, 3);
        assertThat(result, is(3));
    }
    @Test
    public void whenTree() {
        int result = Max.max(3, 3, 5);
        assertThat(result, is(5));
    }
    @Test
    public void whenFour() {
        int result = Max.max(3, 3, 5, 15);
        assertThat(result, is(15));
    }
}
