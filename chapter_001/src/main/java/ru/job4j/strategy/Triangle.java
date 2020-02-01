package ru.job4j.strategy;

public class Triangle implements Shape {
    @Override
    public String draw() {
        return new StringBuilder().append("    +    ")
                                  .append(System.lineSeparator())
                                  .append("   + +   ")
                                  .append(System.lineSeparator())
                                  .append("  +   +  ")
                                  .append(System.lineSeparator())
                                  .append(" +     + ")
                                  .append(System.lineSeparator())
                                  .append("+ + + + +").toString();
    }
}
