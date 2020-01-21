package ru.job4j.inheritance;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EngineerTest {
    String expectedName = "Peter";
    String expectedSurname = "Parker";
    String expectedEdu = "MIT";
    String expectedBirthday = "19.07.1989";

    Engineer peter = new Engineer(expectedName, expectedSurname, expectedEdu, expectedBirthday);

    @Test
    public void peterData() {
        assertThat(peter.getName(), is(expectedName));
        assertThat(peter.getSurname(), is(expectedSurname));
        assertThat(peter.getBirthday(), is(expectedBirthday));
        assertThat(peter.getEducation(), is(expectedEdu));
    }

    @Test
    public void peterPlanning() {
        assertThat(peter.planning(new Task()).getClass().getSimpleName(), is("Plan"));

    }
}
