package ru.job4j.sort;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MachineTest {
    @Test
    public void whenEquals() {
        Machine machine = new Machine();
        int[] expected = {};
        int[] rsl = machine.change(100, 100);
        assertThat(rsl, is(expected));
    }

    @Test
    public void when50by35() {
        Machine machine = new Machine();
        int[] expected = {10, 5};
        int[] rsl = machine.change(50, 35);
        assertThat(rsl, is(expected));
    }

    @Test
    public void when60by35() {
        Machine machine = new Machine();
        int[] expected = {10, 10, 5};
        int[] rsl = machine.change(60, 35);
        assertThat(rsl, is(expected));
    }

    @Test
    public void when60by22() {
        Machine machine = new Machine();
        int[] expected = {10, 10, 10, 5, 2, 1};
        int[] rsl = machine.change(60, 22);
        assertThat(rsl, is(expected));
    }
}
