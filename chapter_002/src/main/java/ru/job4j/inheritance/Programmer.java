package ru.job4j.inheritance;

import java.util.Date;

public class Programmer extends Engineer {

    String enthusiasm;

    public Programmer(String name, String surname, String education, String birthday, String enthusiasm) {
        super(name, surname, education, birthday);
        this.enthusiasm = enthusiasm;
    }

    public String getEnthusiasm() {
        return enthusiasm;
    }

    public Code write(Task task) {
        return new Code();
    }


}
