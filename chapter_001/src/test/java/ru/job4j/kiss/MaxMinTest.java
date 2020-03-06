package ru.job4j.kiss;

import org.junit.Test;
import ru.job4j.stream.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
public class MaxMinTest {
    List<Student> arg = new ArrayList<>(List.of(new Student("Pavlov", 11), new Student("Petrov", 89),
            new Student("Pavlov", 43)));
    @Test
    public void maxTest() {
        Student expected = new Student("Petrov", 89);
        Student actual = new MaxMin().max(arg, Comparator.comparing(Student::getScore));
        assertThat(expected, is(actual));
    }

    @Test
    public void minTest() {
        Student expected = new Student("Pavlov", 11);
        Student actual = new MaxMin().min(arg, Comparator.comparing(Student::getScore));
        assertThat(expected, is(actual));
    }
}
