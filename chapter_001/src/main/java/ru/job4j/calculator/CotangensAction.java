package ru.job4j.calculator;

import ru.job4j.tracker.Input;

import java.util.Locale;
import java.util.function.Consumer;

public class CotangensAction implements CalculateAction {
    private Consumer<String> con;

    public CotangensAction(Consumer<String> con) {
        this.con = con;
    }

    @Override
    public String name() {
        return "Cotangens";
    }

    @Override
    public boolean execute(Input input, Calculator calculator) {
        var a = Double.parseDouble(input.askStr("Enter angle in degrees: "));
        con.accept(String.format(Locale.ROOT, "Cotangens of %s degrees is %.2f", a, ((EngineerCalculator) calculator).ctg(a)));
        return true;
    }
}
