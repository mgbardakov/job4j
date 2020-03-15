package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    public void whenAdd2and2then4() {
        assertThat(calculator.add(2, 2), is(4.d));
    }

    @Test
    public void whenDiv2By2Then1() {
        assertThat(calculator.div(2, 2), is(1.d));
    }

    @Test
    public void whenMultiply2By2Then4() {
        assertThat(calculator.multiply(2, 2), is(4.d));
    }

    @Test
    public void whenSubtract2OutOf2then0() {
        assertThat(calculator.subtract(2, 2), is(0.d));
    }
}