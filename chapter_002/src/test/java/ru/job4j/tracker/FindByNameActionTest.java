package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByNameActionTest {

    @Test
    public void whenItemFindByNameMock() {
        Store store = new MemTracker();
        store.init();
        Item item = new Item("new item");
        store.add(item);
        var bos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bos);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(item.getName());
        new FindByNameAction(ps::println).execute(input, store);
        String sep = System.lineSeparator();
        assertThat(String.format("name: %s id: %s%s", item.getName(),
                item.getId(), sep),
                is(bos.toString()));
    }
}