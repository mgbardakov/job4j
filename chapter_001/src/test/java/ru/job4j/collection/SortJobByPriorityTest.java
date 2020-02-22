package ru.job4j.collection;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;
public class SortJobByPriorityTest {
    @Test
    public void whenAcsByName() {
        int rsl = new SortJobByPriorityAsc().compare(new Job("John", 2), new Job("Alex", 1));
        assertThat(rsl, greaterThan(0));

    }

    @Test
    public void whenDescByName() {
        int rsl = new SortJobByPriorityDesc().compare(new Job("Alf", 0), new Job("Peter", 1));
        assertThat(rsl, greaterThan(0));
    }
}
