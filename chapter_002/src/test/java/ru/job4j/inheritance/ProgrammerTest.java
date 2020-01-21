package ru.job4j.inheritance;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProgrammerTest {
    String expectedName = "Tony";
    String expectedSurname = "Soprano";
    String expectedEdu = "Prinston";
    String expectedBirthday = "21.11.1993";
    String expectedEnt = "high";

    Programmer tony = new Programmer(expectedName, expectedSurname, expectedEdu, expectedBirthday, expectedEnt);

    @Test
    public void tonyData() {
        assertThat(tony.getName(), is(expectedName));
        assertThat(tony.getSurname(), is(expectedSurname));
        assertThat(tony.getBirthday(), is(expectedBirthday));
        assertThat(tony.getEducation(), is(expectedEdu));
        assertThat(tony.getEnthusiasm(), is(expectedEnt));
    }

    @Test
    public void tonyPlanning() {
        assertThat(tony.planning(new Task()).getClass().getSimpleName(), is("Plan"));
    }

    @Test
    public void tonyWriting() {
        assertThat(tony.write(new Task()).getClass().getSimpleName(), is("Code"));
    }
}
