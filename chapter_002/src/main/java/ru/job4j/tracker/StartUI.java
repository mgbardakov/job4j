package ru.job4j.tracker;


public class StartUI {

    public static void createItem(Input input, Tracker tracker) {
        System.out.println("=== Create a new Item ====");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
        System.out.println();
    }

    public static void showAll(Tracker tracker) {
        System.out.println("=== Show all items ===");
        Item[] itemsToShow = tracker.findAll();
        for (Item it : itemsToShow) {
            System.out.println(it);
        }
        System.out.println();
    }

    public static void editItem(Input input, Tracker tracker) {
        String editId = input.askStr("Enter id: ");
        String newName = input.askStr("Enter new name: ");
        if (tracker.replace(editId, new Item(newName))) {
            System.out.println("Editing completed");
        } else {
            System.out.println("Item not found");
        }
        System.out.println();
    }

    public static void deleteItem(Input input, Tracker tracker) {
        String delId = input.askStr("Enter id: ");
        if (tracker.delete(delId)) {
            System.out.println("Item deleted");
        } else {
            System.out.println("Item not found");
        }
        System.out.println();
    }

    public static void findById(Input input, Tracker tracker) {
        String findId = input.askStr("Enter id: ");
        System.out.println(tracker.findById(findId));
        System.out.println();
    }

    public static void findByName(Input input, Tracker tracker) {
        String findName = input.askStr("Enter name: ");
        Item[] similarNames = tracker.findByName(findName);
        for (Item i : similarNames) {
            System.out.println(i);
        }
        System.out.println();
    }

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("Select: ");
            System.out.println();
            switch (select) {
                case 0:
                    createItem(input, tracker);
                    break;
                case 1:
                    showAll(tracker);
                    break;
                case 2:
                    editItem(input, tracker);
                    break;
                case 3:
                    deleteItem(input, tracker);
                    break;
                case 4:
                    findById(input, tracker);
                    break;
                case 5:
                    findByName(input, tracker);
                    break;
                case 6:
                    run = false;
                    break;
                default:
            }

        }
    }


    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }


    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}
        //0. Add new Item
        //1. Show all items
        //2. Edit item
        //3. Delete item
        //4. Find item by Id
        //5. Find items by name
        //6. Exit Program
        //Select: