package ru.job4j.inheritance;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
public class DoctorTest {
    String expectedName = "Steve";
    String expectedSurname = "Rogers";
    String expectedEdu = "Medical school";
    String expectedBirthday = "19.04.1975";

    Doctor steve = new Doctor(expectedName, expectedSurname, expectedEdu, expectedBirthday);

    @Test
    public void steveData() {
        assertThat(steve.getName(), is(expectedName));
        assertThat(steve.getSurname(), is(expectedSurname));
        assertThat(steve.getBirthday(), is(expectedBirthday));
        assertThat(steve.getEducation(), is(expectedEdu));
    }

    @Test
    public void steveDiag() {
        assertThat(steve.heal(new Pacient()).getClass().getSimpleName(), is("Diagnose"));

    }

}
