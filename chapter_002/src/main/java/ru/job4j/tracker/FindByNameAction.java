package ru.job4j.tracker;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "=== Find item(s) by name ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String findName = input.askStr("Enter name: ");
        Item[] similarNames = tracker.findByName(findName);
        for (Item i : similarNames) {
            System.out.println(i);
        }
        System.out.println();
        return true;
    }
}
