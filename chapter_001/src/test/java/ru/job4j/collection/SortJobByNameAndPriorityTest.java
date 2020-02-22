package ru.job4j.collection;
import org.junit.Test;

import java.util.Comparator;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
public class SortJobByNameAndPriorityTest {
    @Test
    public void nameUpPriorityDownTest() {
        Comparator<Job> nameUpPriorityDown = new SortJobByNameAsc().thenComparing(new SortJobByPriorityDesc());
        int rsl1 = nameUpPriorityDown.compare(new Job("Alex", 1), new Job("James", 1));
        int rsl2 = nameUpPriorityDown.compare(new Job("Alex", 1), new Job("Alex", 2));
        assertThat(rsl1, lessThan(0));
        assertThat(rsl2, greaterThan(0));
    }

    @Test
    public void priorityUpNameDownTest() {
        Comparator<Job> priorityUpNameDown = new SortJobByPriorityAsc().thenComparing(new SortJobByNameDesc());
        int rsl1 = priorityUpNameDown.compare(new Job("Alex", 1), new Job("James", 2));
        int rsl2 = priorityUpNameDown.compare(new Job("Alex", 1), new Job("John", 1));
        assertThat(rsl1, lessThan(0));
        assertThat(rsl2, greaterThan(0));
    }
}
