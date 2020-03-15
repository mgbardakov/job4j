package ru.job4j.calculator;

import ru.job4j.tracker.Input;

public class ExitAction implements CalculateAction {
    @Override
    public String name() {
        return "Exit";
    }

    @Override
    public boolean execute(Input input, Calculator calculator) {
        return false;
    }
}
