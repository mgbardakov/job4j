package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
public class SqMaxTest {

    @Test
    public void whenFirstMax() {
        int result = SqMax.max(10, 4, 2, 5);
        assertThat(result, is(10));
    }

    @Test
    public void whenSecondMax() {
        int result = SqMax.max(1, 10, 2, 5);
        assertThat(result, is(10));
    }

    @Test
    public void whenThirdMax() {
        int result = SqMax.max(1, 10, 20, 5);
        assertThat(result, is(20));
    }

    @Test
    public void whenFourthMax() {
        int result = SqMax.max(1, 10, 10, 20);
        assertThat(result, is(20));
    }

    @Test
    public void whenAllEqual() {
        int result = SqMax.max(1, 1, 1, 1);
        assertThat(result, is(1));
    }
}
