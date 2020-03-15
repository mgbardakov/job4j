package ru.job4j.calculator;

import ru.job4j.tracker.Input;

import java.util.function.Consumer;

public class SubtractAction implements CalculateAction {
    private Consumer<String> con;

    public SubtractAction(Consumer<String> con) {
        this.con = con;
    }

    @Override
    public String name() {
        return "Subtract";
    }

    @Override
    public boolean execute(Input input, Calculator calculator) {
        var a = Double.parseDouble(input.askStr("Enter reduced: "));
        var b = Double.parseDouble(input.askStr("Enter subtracted: "));
        con.accept(String.format("%s - %s = %s", a, b, calculator.subtract(a, b)));
        return true;
    }
}
