package ru.job4j.condition;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {
    @Test
    public void distance () {
        int x1 = 1, y1 = -3, x2 = 11, y2 = 0;
        double expected = 10.44;
        double out = Point.distance(x1, y1, x2, y2);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void distance2 () {
        int x1 = 0, y1 = 0, x2 = 2, y2 = 0;
        double expected = 2.0;
        double out = Point.distance(x1, y1, x2, y2);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void distance3 () {
        int x1 = 0, y1 = 0, x2 = 12, y2 = -5;
        double expected = 13.0;
        double out = Point.distance(x1, y1, x2, y2);
        Assert.assertEquals(expected, out, 0.01);
    }

}
