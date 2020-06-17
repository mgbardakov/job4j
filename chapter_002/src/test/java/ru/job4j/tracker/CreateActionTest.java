package ru.job4j.tracker;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;

public class CreateActionTest {

    @Test
    public void whenItemCreated() {
        Store store = new MemTracker();
        store.init();
        Input input = new StubInput(new String[]{"TestName"});
        new CreateAction().execute(input, store);
        Item result = store.findByName("TestName").get(0);
        Item expected = new Item("TestName");
        assertThat(result.getName(), is(expected.getName()));
        store.delete(result.getId());
    }
}