package ru.job4j.tracker;

import java.util.List;

public class ShowAllAction implements UserAction {
    @Override
    public String name() {
        return "=== Show all items ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        List<Item> itemsToShow = tracker.findAll();
        for (Item it : itemsToShow) {
            System.out.println(it);
        }
        return true;
    }
}
