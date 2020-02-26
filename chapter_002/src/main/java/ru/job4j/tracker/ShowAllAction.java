package ru.job4j.tracker;

import java.util.List;
import java.util.function.Consumer;

public class ShowAllAction implements UserAction {
    Consumer<String> con;
    @Override
    public String name() {
        return "=== Show all items ===";
    }

    public ShowAllAction() {
        con = System.out::println;
    }

    public ShowAllAction(Consumer<String> con) {
        this.con = con;
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        List<Item> itemsToShow = tracker.findAll();
        itemsToShow.stream().map(Item::toString).forEach(con);
        return true;
    }
}
