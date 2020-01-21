package ru.job4j.inheritance;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BuilderTest {
    String expectedName = "Vito";
    String expectedSurname = "Corleone";
    String expectedEdu = "Yale";
    String expectedBirthday = "13.09.1984";

    Builder vito = new Builder(expectedName, expectedSurname, expectedEdu, expectedBirthday);

    @Test
    public void vitoData() {
        assertThat(vito.getName(), is(expectedName));
        assertThat(vito.getSurname(), is(expectedSurname));
        assertThat(vito.getBirthday(), is(expectedBirthday));
        assertThat(vito.getEducation(), is(expectedEdu));
    }

    @Test
    public void tonyPlanning() {
        assertThat(vito.planning(new Task()).getClass().getSimpleName(), is("Plan"));
    }

    @Test
    public void vitoBuild() {
        assertThat(vito.build(new Brick[40000]).getClass().getSimpleName(), is("Building"));
    }
}
