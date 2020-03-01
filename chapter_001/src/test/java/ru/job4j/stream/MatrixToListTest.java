package ru.job4j.stream;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class MatrixToListTest {
    @Test
    public void matrixToList() {
        Integer[][] arg = new Integer[][] {
                new Integer[] {1, 2, 3, 4},
                new Integer[] {5, 6, 7, 8},
                new Integer[] {9, 10, 11, 12},
                new Integer[] {13, 14, 15, 16}
        };
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        List<Integer> actual = new MatrixToList().matrixToList(arg);
        assertThat(expected, is(actual));
    }
}
