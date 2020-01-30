package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenItemCreated() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[] {"Bug"});
        StartUI.createItem(input, tracker);
        Item result = tracker.findAll()[0];
        Item expected = new Item("Bug");
        assertThat(result.getName(), is(expected.getName()));
    }
}
