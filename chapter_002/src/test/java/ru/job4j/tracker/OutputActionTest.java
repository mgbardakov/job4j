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
    private final PrintStream def = System.out;
    Tracker tracker = new Tracker();
    @Before
    public void doBefore() {
        System.setOut(new PrintStream(bos));
        tracker.add(new Item("Pan"));
        tracker.add(new Item("Cake"));
        tracker.add(new Item("Pan"));
    }

    @After
    public void doAfter() {
        System.setOut(def);
    }


    @Test
    public void whenShowAll() {
        List<Item> items = tracker.findAll();
        new ShowAllAction().execute(input, tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(items.get(0).toString())
                .add(items.get(1).toString())
                .add(items.get(2).toString())
                .toString();
        assertThat(new String(bos.toByteArray()), is(expect));
    }

    @Test
    public void whenFindByName() {
        List<Item> items = tracker.findByName("Pan");
        new FindByNameAction().execute(input, tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(items.get(0).toString())
                .add(items.get(1).toString())
                .toString();
        assertThat(new String(bos.toByteArray()), is(expect));
    }
}
