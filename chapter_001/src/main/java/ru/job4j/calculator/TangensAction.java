package ru.job4j.calculator;

import ru.job4j.tracker.Input;

import java.util.function.Consumer;

public class TangensAction implements CalculateAction {
    private Consumer<String> con;

    public TangensAction(Consumer<String> con) {
        this.con = con;
    }

    @Override
    public String name() {
        return "Tangens";
    }

    @Override
    public boolean execute(Input input, Calculator calculator) {
        var a = Double.parseDouble(input.askStr("Enter angle in degrees: "));
        con.accept(String.format("Tangens of %s degrees is %.2f", a, ((EngineerCalculator) calculator).tg(a)));
        return true;
    }
}
