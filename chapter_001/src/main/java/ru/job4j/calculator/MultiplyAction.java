package ru.job4j.calculator;

import ru.job4j.tracker.Input;

import java.util.function.Consumer;

public class MultiplyAction implements  CalculateAction {
    private Consumer<String> con;

    public MultiplyAction(Consumer<String> con) {
        this.con = con;
    }

    @Override
    public String name() {
        return "Multiply";
    }

    @Override
    public boolean execute(Input input, Calculator calculator) {
        var a = Double.parseDouble(input.askStr("Enter first multiplier: "));
        var b = Double.parseDouble(input.askStr("Enter second multiplier: "));
        con.accept(String.format("%s * %s = %s", a, b, calculator.multiply(a, b)));
        return true;
    }
}
