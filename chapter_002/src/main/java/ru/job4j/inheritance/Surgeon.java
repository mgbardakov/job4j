package ru.job4j.inheritance;

import java.util.Date;

public class Surgeon extends Doctor {

    String scalpel;

    public Surgeon(String name, String surname, String education, String birthday, String scalpel) {
        super(name, surname, education, birthday);
        this.scalpel = scalpel;
    }

    public String getScalpel() {
        return scalpel;
    }

    public Operation operate(Pacient pacient) {
        return new Operation();
    }
}
