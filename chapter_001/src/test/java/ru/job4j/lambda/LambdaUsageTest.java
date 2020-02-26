package ru.job4j.lambda;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LambdaUsageTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        LambdaUsage function = new LambdaUsage();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticThenQuadraticResults() {
        LambdaUsage function = new LambdaUsage();
        List<Double> result = function.diapason(5, 7, x -> Math.pow(x, 2));
        List<Double> expected = Arrays.asList(25D, 36D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogThenLogResults() {
        LambdaUsage function = new LambdaUsage();
        List<Double> result = function.diapason(6, 8, Math::log);
        List<Double> expected = Arrays.asList(1.79, 1.94);
        double[] res = result.stream().mapToDouble(Number::doubleValue).toArray();
        double[] exp = expected.stream().mapToDouble(Number::doubleValue).toArray();
        assertArrayEquals(exp, res, 0.01);

    }
}
