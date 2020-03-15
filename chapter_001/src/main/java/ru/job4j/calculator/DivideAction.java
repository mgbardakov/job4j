package ru.job4j.calculator;

import ru.job4j.tracker.Input;

import java.util.function.Consumer;

public class DivideAction implements CalculateAction {
    private Consumer<String> con;

    public DivideAction(Consumer<String> con) {
        this.con = con;
    }

    @Override
    public String name() {
        return "Divide";
    }

    @Override
    public boolean execute(Input input, Calculator calculator) {
        var run = true;
        while (run) {
            var a = Double.parseDouble(input.askStr("Enter divided: "));
            var b = Double.parseDouble(input.askStr("Enter divided: "));
            if (b != 0) {
                con.accept(String.format("%s / %s = %s", a, b, calculator.div(a, b)));
                run = false;
            } else {
                System.out.println("Division by zero try again");
            }
        }
        return true;
    }
}
