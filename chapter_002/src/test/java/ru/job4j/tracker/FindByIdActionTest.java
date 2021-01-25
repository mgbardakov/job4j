package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByIdActionTest {

    @Test
    public void whenItemFindByIDMock() {
        Store store = new MemTracker();
        store.init();
        Item item = new Item("new item");
        store.add(item);
        var bos = new ByteArrayOutputStream();
        PrintStream con = System.out;
        PrintStream ps = new PrintStream(bos);
        System.setOut(ps);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(item.getId());
        new FindByIdAction().execute(input, store);
        System.setOut(con);
        String sep = System.lineSeparator();
        assertThat(String.format("name: %s id: %s%s%s", item.getName(),
                item.getId(), sep, sep),
                is(bos.toString()));
    }
}