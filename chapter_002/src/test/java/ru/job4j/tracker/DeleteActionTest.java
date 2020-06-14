package ru.job4j.tracker;
import org.junit.Test;

import static org.junit.Assert.*;
public class DeleteActionTest {

    @Test
    public void whenDeleteItem() {
        Store store = new SqlTracker();
        store.init();
        Item item = new Item("new item");
        store.add(item);
        String[] answers = {item.getId()};
        new DeleteAction().execute(new StubInput(answers), store);
        Item deleted = store.findById(item.getId());
        assertNull(deleted);
    }
}
