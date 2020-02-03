package ru.job4j.tracker;

public class StubAction implements UserAction {
    private boolean call;

    @Override
    public String name() {
        return "StubAction";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        call = true;
        return false;
    }

    public boolean isCall() {
        return call;
    }

}
