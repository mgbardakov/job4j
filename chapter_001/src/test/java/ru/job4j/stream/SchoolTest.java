package ru.job4j.stream;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SchoolTest {
    List<Student> list = Arrays.asList(new Student("Karamazov", 30),
                                       new Student("Pavlov", 60),
                                       new Student("Petrov", 80),
                                       new Student("Vasiliev", 40),
                                       new Student("Abramov", 75),
                                       new Student("Lee", 91));
    @Test
    public void getAList() {
        List<Student> result = new School().collect(list, x -> x.getScore() >= 70 && x.getScore() <= 100);
        List<Student> expected = Arrays.asList(new Student("Petrov", 80), new Student("Abramov", 75),
                                                            new Student("Lee", 91));
        assertThat(result, is(expected));
    }
    @Test
    public void getBList() {
        List<Student> result = new School().collect(list, x -> x.getScore() >= 50 && x.getScore() < 70);
        List<Student> expected = Collections.singletonList(new Student("Pavlov", 60));
        assertThat(result, is(expected));
    }

    @Test
    public void getCList() {
        List<Student> result = new School().collect(list, x -> x.getScore() > 0 && x.getScore() < 50);
        List<Student> expected = Arrays.asList(new Student("Karamazov", 30),
                                              new Student("Vasiliev", 40));
        assertThat(result, is(expected));
    }

    @Test
    public void studentListToMap() {
        Map<String, Student> expected = new HashMap<>();
        expected.put("Karamazov", new Student("Karamazov", 30));
        expected.put("Pavlov", new Student("Pavlov", 60));
        expected.put("Petrov", new Student("Petrov", 80));
        expected.put("Vasiliev", new Student("Vasiliev", 40));
        expected.put("Abramov", new Student("Abramov", 75));
        expected.put("Lee", new Student("Lee", 91));
        Map<String, Student> result = new School().studentMap(list);
        assertThat(result, is(expected));

    }

}
