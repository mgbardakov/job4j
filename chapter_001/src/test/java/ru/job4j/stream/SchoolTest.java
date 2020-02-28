package ru.job4j.stream;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SchoolTest {
    List<Student> list = Arrays.asList(new Student(30),
                                       new Student(60),
                                       new Student(80),
                                       new Student(40),
                                       new Student(75),
                                       new Student(91));
    @Test
    public void getAList() {
        List<Student> result = new School().collect(list, x -> x.getScore() >= 70 && x.getScore() <= 100);
        List<Student> expected = Arrays.asList(new Student(80), new Student(75), new Student(91));
        assertThat(result, is(expected));
    }
    @Test
    public void getBList() {
        List<Student> result = new School().collect(list, x -> x.getScore() >= 50 && x.getScore() < 70);
        List<Student> expected = Collections.singletonList(new Student(60));
        assertThat(result, is(expected));
    }

    @Test
    public void getCList() {
        List<Student> result = new School().collect(list, x -> x.getScore() > 0 && x.getScore() < 50);
        List<Student> expected = Arrays.asList(new Student(30), new Student(40));
        assertThat(result, is(expected));
    }

}
