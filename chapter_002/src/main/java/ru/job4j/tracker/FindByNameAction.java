package ru.job4j.tracker;

import java.util.List;
import java.util.function.Consumer;

public class FindByNameAction implements UserAction {
    private Consumer<String> con;

    public FindByNameAction(Consumer<String> con) {
        this.con = con;
    }
    public FindByNameAction() {
        this.con = System.out::println;
    }

    @Override
    public String name() {
        return "=== Find item(s) by name ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String findName = input.askStr("Enter name: ");
        List<Item> similarNames = tracker.findByName(findName);
        similarNames.stream().map(Item::toString).forEach(con);
        return true;
    }
}
