package ru.job4j.collection;

import org.hamcrest.core.Is;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DepartmentsTest {
    @Test
    public void whenMissed() {
        List<String> input = List.of("k1/sk1/ssk2");
        List<String> expect = List.of("k1", "k1/sk1", "k1/sk1/ssk2");
        List<String> result = Departments.fillGaps(input);
        assertThat(result, is(expect));
    }

    @Test
    public void whenNonChange() {
        List<String> input = List.of("k1", "k1/sk1");
        List<String> expect = List.of("k1", "k1/sk1");
        List<String> result = Departments.fillGaps(input);
        assertThat(result, is(expect));
    }

    @Test
    public void descTest() {
        List<String> list = Departments.fillGaps(List.of("K1/SK1/SSK1", "K1/SK1/SSK2", "K2/SK1/SSK1", "K2/SK1/SSK2", "K1/SK2"));
        Departments.sortDesc(list);
        assertThat(list, Is.is(List.of("K2",
                                            "K2/SK1",
                                            "K2/SK1/SSK2",
                                            "K2/SK1/SSK1",
                                            "K1",
                                            "K1/SK2",
                                            "K1/SK1",
                                            "K1/SK1/SSK2",
                                            "K1/SK1/SSK1")));
    }

    @Test
    public void ascTest() {
        List<String> list = Departments.fillGaps(List.of("K1/SK1/SSK1", "K1/SK1/SSK2", "K2/SK1/SSK1", "K2/SK1/SSK2", "K1/SK2"));
        Departments.sortAsc(list);
        assertThat(list, Is.is(List.of("K1",
                                            "K1/SK1",
                                            "K1/SK1/SSK1",
                                            "K1/SK1/SSK2",
                                            "K1/SK2",
                                            "K2",
                                            "K2/SK1",
                                            "K2/SK1/SSK1",
                                            "K2/SK1/SSK2")));
    }
}