package ru.job4j.tracker;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;

public class EditActionTest {
    @Test
    public void whenReplaceItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {
                item.getId(),
                "replaced item"
        };
        new EditAction().execute(new StubInput(answers), tracker);
        Item replaced = tracker.findById(item.getId());
        assertThat(replaced.getName(), is("replaced item"));
    }
}
