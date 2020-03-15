package ru.job4j.calculator;

import ru.job4j.tracker.Input;

public class StubCalcAction implements CalculateAction {
    private boolean call;

    @Override
    public String name() {
        return "StubAction";
    }

    @Override
    public boolean execute(Input input, Calculator calculator) {
        call = true;
        return false;
    }

    public boolean isCall() {
        return call;
    }
}
