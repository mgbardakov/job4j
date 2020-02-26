package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StartUI {
    private final Input input;
    private final Tracker tracker;
    private final Consumer<String> output;
    private final List<UserAction> actions;

    public StartUI(Input input, Tracker tracker, Consumer<String> output, List<UserAction> actions) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
        this.actions = actions;
    }

    public void init() {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("Select: ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
        }
    }

    private void showMenu() {
        output.accept("Menu.");
        for (int index = 0; index < actions.size(); index++) {
            output.accept(index + ". " + actions.get(index).name());
        }
    }

    public static void main(String[] args) {
        Input input = new ValidateInput(new ConsoleInput());
        Tracker tracker = new Tracker();
        List<UserAction> actions = new ArrayList<>();
                actions.add(new CreateAction());
                actions.add(new ShowAllAction());
                actions.add(new EditAction());
                actions.add(new DeleteAction());
                actions.add(new FindByIdAction());
                actions.add(new FindByNameAction());
                actions.add(new ExitAction());
        new StartUI(input, tracker, System.out::println, actions).init();
    }
}