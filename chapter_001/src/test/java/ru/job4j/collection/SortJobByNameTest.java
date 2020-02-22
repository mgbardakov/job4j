package ru.job4j.collection;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;
public class SortJobByNameTest {
    @Test
    public void whenAcsByName() {
        int rsl = new SortJobByNameAsc().compare(new Job("John", 1), new Job("Alex", 2));
        assertThat(rsl, greaterThan(0));

    }

    @Test
    public void whenDescByName() {
        int rsl = new SortJobByNameDesc().compare(new Job("Alf", 0), new Job("Peter", 0));
        assertThat(rsl, greaterThan(0));
    }
}
