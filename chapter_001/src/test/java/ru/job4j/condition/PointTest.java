package ru.job4j.condition;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {
    @Test
    public void distance() {
        Point first = new Point(1, -3);
        Point second = new Point(11, 0);
        double expected = 10.44;
        double out = first.distance(second);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void distance2() {
        Point first = new Point(0, 0);
        Point second = new Point(2, 0);
        double expected = 2.0;
        double out = first.distance(second);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void distance3() {
        Point first = new Point(0, 0);
        Point second = new Point(12, -5);
        double expected = 13.0;
        double out = first.distance(second);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void distance3D1() {
        Point first = new Point(2, 3, 3);
        Point second = new Point(12, -5, 11);
        double expected = 15.1;
        double out = first.distance3d(second);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void distance3D2() {
        Point first = new Point(4, 7, 10);
        Point second = new Point(12, -5, 6);
        double expected = 14.97;
        double out = first.distance3d(second);
        Assert.assertEquals(expected, out, 0.01);
    }

}
