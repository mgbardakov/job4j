package ru.job4j.inheritance;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SurgeonTest {
    private String expectedName = "Tom";
    private String expectedSurname = "Clancy";
    private String expectedEdu = "Harvard";
    private String expectedBirthday = "21.06.1977";
    private String expectedScalpel = "Sharp";

    Surgeon tom = new Surgeon(expectedName, expectedSurname, expectedEdu, expectedBirthday, expectedScalpel);

    @Test
    public void peterData() {
        assertThat(tom.getName(), is(expectedName));
        assertThat(tom.getSurname(), is(expectedSurname));
        assertThat(tom.getBirthday(), is(expectedBirthday));
        assertThat(tom.getEducation(), is(expectedEdu));
        assertThat(tom.getScalpel(), is(expectedScalpel));
    }

    @Test
    public void tomDiag() {
        assertThat(tom.heal(new Pacient()).getClass().getSimpleName(), is("Diagnose"));
    }

    @Test
    public void tomOperating() {
        assertThat(tom.operate(new Pacient()).getClass().getSimpleName(), is("Operation"));
    }


}