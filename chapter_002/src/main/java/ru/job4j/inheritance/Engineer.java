package ru.job4j.inheritance;

import java.util.Date;

public class Engineer extends Profession {

    private boolean knowsMath;

    public Engineer(String name, String surname, String education, String birthday) {
        super(name, surname, education, birthday);
        this.knowsMath = true;
    }

    public Plan planning(Task task) {
        return new Plan();
    }
}
