package ru.job4j.inheritance;

public class Builder extends Engineer {
    private boolean knowsSopromat;

    public Builder(String name, String surname, String education, String birthday) {
        super(name, surname, education, birthday);
        this.knowsSopromat = true;
    }

    public Building build(Brick[] bricks) {
        return new Building();
    }
}
