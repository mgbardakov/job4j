package ru.job4j.inheritance;

public class Dentist extends Doctor {

    private String dentistChair;

    public Dentist(String name, String surname, String education, String birthday, String dentistChair) {
        super(name, surname, education, birthday);
        this.dentistChair = dentistChair;
    }

    public String getDentistChair() {
        return dentistChair;
    }

    public Tooth remove(Pacient pacient) {
        return new Tooth();
    }
}
