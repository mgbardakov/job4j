package ru.job4j.tracker;

public class EditAction implements UserAction {
    @Override
    public String name() {
        return "=== Edit Item ====";
    }

    @Override
    public boolean execute(Input input, Store store) {
        String editId = input.askStr("Enter id: ");
        String newName = input.askStr("Enter new name: ");
        if (store.replace(editId, new Item(newName))) {
            System.out.println("Editing completed");
        } else {
            System.out.println("Item not found");
        }
        System.out.println();
        return true;
    }
}
