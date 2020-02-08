package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidateInputTest {

    private ByteArrayOutputStream mem;
    private PrintStream out;

    @Before
    public void doBefore() {
        mem = new ByteArrayOutputStream();
        out = System.out;
        System.setOut(new PrintStream(mem));
    }

    @After
    public void doAfter() {
        System.setOut(out);
    }
    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"one", "0"})
        );
        input.askInt("Enter");
        assertThat(
                mem.toString(),
                is(String.format("Please enter validate data again.%n"))
        );
    }

    @Test
    public void whenInvalidInputByRange() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"2", "0"})
        );
        input.askInt("Enter", 1);
        assertThat(
                mem.toString(),
                is(String.format("Please select key from menu.%n"))
        );
    }

}
