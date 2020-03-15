package ru.job4j.calculator;

import ru.job4j.tracker.Input;
public interface CalculateAction {
    String name();

    boolean execute(Input input, Calculator calculator);
}
