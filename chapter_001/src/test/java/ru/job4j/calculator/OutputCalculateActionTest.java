package ru.job4j.calculator;

import org.junit.Test;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.StubInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OutputCalculateActionTest {

    @Test
    public void when2And2SimpleCalculator() {
        Input input = new StubInput(new String[] {"2", "2", "2", "2", "2", "2", "2", "2"});
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bos);
        Calculator calculator = new Calculator(printStream::println);
        List<CalculateAction> list = calculator.getActionList();
        list.get(0).execute(input, calculator);
        list.get(1).execute(input, calculator);
        list.get(2).execute(input, calculator);
        list.get(3).execute(input, calculator);
        assertThat(bos.toString(), is("2.0 + 2.0 = 4.0"
                                      + System.lineSeparator()
                                      + "2.0 - 2.0 = 0.0"
                                      + System.lineSeparator()
                                      + "2.0 * 2.0 = 4.0"
                                      + System.lineSeparator()
                                      + "2.0 / 2.0 = 1.0"
                                      + System.lineSeparator()));
    }

    @Test
    public void when30DegreeEngineerCalculator() {
        Input input = new StubInput(new String[] {"30", "30", "30", "30"});
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bos);
        Calculator calculator = new EngineerCalculator(printStream::println);
        List<CalculateAction> list = calculator.getActionList();
        list.get(4).execute(input, calculator);
        list.get(5).execute(input, calculator);
        list.get(6).execute(input, calculator);
        list.get(7).execute(input, calculator);
        assertThat(bos.toString(), is("Sinus of 30.0 degrees is 0,50"
                + System.lineSeparator()
                + "Cosinus of 30.0 degrees is 0,87"
                + System.lineSeparator()
                + "Tangens of 30.0 degrees is 0,58"
                + System.lineSeparator()
                + "Cotangens of 30.0 degrees is 1,73"
                + System.lineSeparator()));
    }
}