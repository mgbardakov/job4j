package ru.job4j.inheritance;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
public class DentistTest {
    private String expectedName = "Bill";
    private String expectedSurname = "Burr";
    private String expectedEdu = "Stanford";
    private String expectedBirthday = "14.08.1974";
    private String expectedChair = "Dentist chair";

    Dentist bill = new Dentist(expectedName, expectedSurname, expectedEdu, expectedBirthday, expectedChair);

    @Test
    public void billData() {
        assertThat(bill.getName(), is(expectedName));
        assertThat(bill.getSurname(), is(expectedSurname));
        assertThat(bill.getBirthday(), is(expectedBirthday));
        assertThat(bill.getEducation(), is(expectedEdu));
        assertThat(bill.getDentistChair(), is(expectedChair));
    }

    @Test
    public void billDiag() {
        assertThat(bill.heal(new Pacient()).getClass().getSimpleName(), is("Diagnose"));

    }

    @Test
    public void billRemove() {
        assertThat(bill.remove(new Pacient()).getClass().getSimpleName(), is("Tooth"));

    }

}
