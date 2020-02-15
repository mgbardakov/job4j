package ru.job4j.tracker;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenStub() {
        Input input = new StubInput(new String[]{"0"});
        StubAction stubAction = new StubAction();
        List<UserAction> list = new ArrayList<>();
        list.add(stubAction);
        new StartUI().init(input, new Tracker(), list);
        assertThat(stubAction.isCall(), is(true));
    }

    @Test
    public void whenCheckInitOutput() {
        Input input = new StubInput(new String[]{"0"});
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        List<UserAction> list = new ArrayList<>();
        list.add(new StubAction());
        new StartUI().init(input, new Tracker(), list);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Menu.")
                .add("0. StubAction")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }
}
