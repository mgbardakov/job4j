package ru.job4j.inheritance;

import java.util.Date;

public class Doctor extends Profession {

    boolean license;

    public Doctor(String name, String surname, String education, String birthday) {
        super(name, surname, education, birthday);
        this.license = true;
    }

    public Diagnose heal(Pacient pacient) {
        return new Diagnose();
    }
}
