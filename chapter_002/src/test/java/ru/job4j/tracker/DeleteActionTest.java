package ru.job4j.tracker;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteActionTest {

    @Test
    public void whenDeleteItem() {
        Store store = new MemTracker();
        store.init();
        Item item = new Item("new item");
        store.add(item);
        String[] answers = {String.valueOf(item.getId())};
        new DeleteAction().execute(new StubInput(answers), store);
        Item deleted = store.findById(String.valueOf(item.getId()));
        assertNull(deleted);
    }

    @Test
    public void whenDeleteItemMock() {
        Store store = new MemTracker();
        store.init();
        Item item = new Item("new item");
        store.add(item);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(String.valueOf(item.getId()));
        new DeleteAction().execute(input, store);
        Item deleted = store.findById(String.valueOf(item.getId()));
        assertNull(deleted);
    }
}
