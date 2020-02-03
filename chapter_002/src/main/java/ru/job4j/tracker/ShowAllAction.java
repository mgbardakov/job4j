package ru.job4j.tracker;

public class ShowAllAction implements UserAction {
    @Override
    public String name() {
        return "=== Show all items ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        Item[] itemsToShow = tracker.findAll();
        for (Item it : itemsToShow) {
            System.out.println(it);
        }
        System.out.println();
        return true;
    }
}
