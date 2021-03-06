package ru.job4j.tracker;

public class FindByIdAction implements UserAction {
    @Override
    public String name() {
        return "=== Find item by ID ====";
    }

    @Override
    public boolean execute(Input input, Store store) {
        String findId = input.askStr("Enter id: ");
        System.out.println(store.findById(findId));
        System.out.println();
        return true;
    }
}
