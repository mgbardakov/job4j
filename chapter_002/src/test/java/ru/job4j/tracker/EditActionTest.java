package ru.job4j.tracker;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;

public class EditActionTest {
    @Test
    public void whenReplaceItem() {
        Store store = new MemTracker();
        store.init();
        Item item = new Item("new item");
        store.add(item);
        String[] answers = {
                item.getId(),
                "replaced item"
        };
        new EditAction().execute(new StubInput(answers), store);
        Item replaced = store.findById(item.getId());
        assertThat(replaced.getName(), is("replaced item"));
        store.delete(item.getId());
    }
}
