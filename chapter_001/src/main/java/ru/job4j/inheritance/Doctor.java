package ru.job4j.inheritance;

public class Doctor extends Profession {

    private boolean license;

    public Doctor(String name, String surname, String education, String birthday) {
        super(name, surname, education, birthday);
        this.license = true;
    }

    public Diagnose heal(Pacient pacient) {
        return new Diagnose();
    }
}
