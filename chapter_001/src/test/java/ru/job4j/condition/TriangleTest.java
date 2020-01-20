package ru.job4j.condition;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TriangleTest {
    @Test
    public void whenExistsThenArea() {
        Triangle testTriangle = new Triangle(new Point(2, 0), new Point(0, 0), new Point(0, 2));
        double result = testTriangle.area();
        Assert.assertEquals(1.99, result, 0.01);
    }
    @Test
    public void whenNotExistThenMinus1() {
        Triangle testTriangle = new Triangle(new Point(1, 1), new Point(2, 2), new Point(3, 3));
        double result = testTriangle.area();
        assertThat(result, is(-1.0));
    }
}
