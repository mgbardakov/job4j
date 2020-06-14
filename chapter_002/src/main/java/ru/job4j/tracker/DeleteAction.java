package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "=== Delete Item ====";
    }

    @Override
    public boolean execute(Input input, Store store) {
        String delId = input.askStr("Enter id: ");
        if (store.delete(delId)) {
            System.out.println("Item deleted");
        } else {
            System.out.println("Item not found");
        }
        System.out.println();
        return true;
    }
}
