package ru.job4j.inheritance;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProgrammerTest {
    private String expectedName = "Tony";
    private String expectedSurname = "Soprano";
    private String expectedEdu = "Prinston";
    private String expectedBirthday = "21.11.1993";
    private String expectedEnt = "high";

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
