package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OutputActionTest {
    private final Input input = new StubInput(new String[]{"Pan"});
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    PrintStream print;
    Item pan;
    Item cake;
    Item pan1;
    Store store = new SqlTracker();
    @Before
    public void doBefore() {
        store.init();
        print = new PrintStream(bos);
        pan = new Item("Pan");
        store.add(pan);
        cake = new Item("Cake");
        store.add(cake);
        pan1 = new Item("Pan");
        store.add(pan1);

    }

    @After
    public void doAfter() {
        store.delete(pan.getId());
        store.delete(cake.getId());
        store.delete(pan1.getId());
    }


    @Test
    public void whenShowAll() {
        List<Item> items = store.findAll();
        new ShowAllAction(print::println).execute(input, store);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(items.get(0).toString())
                .add(items.get(1).toString())
                .add(items.get(2).toString())
                .toString();
        assertThat(new String(bos.toByteArray()), is(expect));
    }

    @Test
    public void whenFindByName() {
        List<Item> items = store.findByName("Pan");
        new FindByNameAction(print::println).execute(input, store);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(items.get(0).toString())
                .add(items.get(1).toString())
                .toString();
        assertThat(new String(bos.toByteArray()), is(expect));
    }
}
