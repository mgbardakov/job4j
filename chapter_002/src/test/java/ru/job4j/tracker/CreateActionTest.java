package ru.job4j.tracker;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;

public class CreateActionTest {

    @Test
    public void whenItemCreated() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"Bug"});
        new CreateAction().execute(input, tracker);
        Item result = tracker.findAll().get(0);
        Item expected = new Item("Bug");
        assertThat(result.getName(), is(expected.getName()));
    }
}