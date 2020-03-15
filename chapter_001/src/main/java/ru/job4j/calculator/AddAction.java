package ru.job4j.calculator;

import ru.job4j.tracker.Input;

import java.util.function.Consumer;

public class AddAction implements CalculateAction {
    private Consumer<String> con;

    public AddAction(Consumer<String> con) {
        this.con = con;
    }

    @Override
    public String name() {
        return "Add";
    }

    @Override
    public boolean execute(Input input, Calculator calculator) {
        var a = Double.parseDouble(input.askStr("Enter first term: "));
        var b = Double.parseDouble(input.askStr("Enter second term: "));
        con.accept(String.format("%s + %s = %s", a, b, calculator.add(a, b)));
        return true;
    }
}
