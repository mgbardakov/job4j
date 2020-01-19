package ru.job4j.oop;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BatteryTest {
    @Test
    public void whenFirst10second5thenFirstEmptySecond15() {
        Battery first = new Battery(10);
        Battery second = new Battery(5);
        first.exchange(second);
        assertThat(first.getLoad(), is(0));
        assertThat(second.getLoad(), is(15));
    }
}
