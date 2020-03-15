package ru.job4j.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class EngineerCalculatorTest {

    @Test
    public void whenSin30() {
        assertEquals(0.5, new EngineerCalculator().sin(30), 0.01);
    }

    @Test
    public void whenCos30() {
        assertEquals(0.87, new EngineerCalculator().cos(30), 0.01);
    }

    @Test
    public void whenTg30() {
        assertEquals(0.58, new EngineerCalculator().tg(30), 0.01);
    }

    @Test
    public void ctg() {
        assertEquals(1.73, new EngineerCalculator().ctg(30), 0.01);
    }
}