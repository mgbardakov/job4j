package ru.job4j.calculator;

import ru.job4j.tracker.ConsoleInput;
import ru.job4j.tracker.Input;

import java.util.function.Consumer;

public class CalculatorUI {
    private final Input input;
    private final Calculator calculator;
    private final Consumer<String> output;

    public CalculatorUI(Input input, Calculator calculator, Consumer<String> output) {
        this.input = input;
        this.calculator = calculator;
        this.output = output;
    }

    public void init() {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("Select: ", calculator.getActionList().size());
            CalculateAction action = calculator.getActionList().get(select);
            run = action.execute(input, calculator);
        }
    }

    private void showMenu() {
        output.accept("Menu.");
        for (int index = 0; index < calculator.getActionList().size(); index++) {
            output.accept(index + ". " + calculator.getActionList().get(index).name());
        }
    }
    public static void main(String[] args) {
        Calculator calculator = new EngineerCalculator();
        Input input = new ConsoleInput();
        new CalculatorUI(input, calculator, System.out::println).init();
    }
}
