package ru.job4j.calculator;

import org.junit.Test;
import ru.job4j.tracker.ConsoleInput;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.StubInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculatorUITest {

    @Test
    public void whenCalculatorInit() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bos);
        Calculator calculator = new Calculator(printStream::println);
        Input stubInput = new StubInput(new String[]{"4"});
        new CalculatorUI(stubInput, calculator, printStream::println).init();
        assertThat(bos.toString(), is("Menu."
                                      + System.lineSeparator()
                                      + "0. Add"
                                      + System.lineSeparator()
                                      + "1. Subtract"
                                      + System.lineSeparator()
                                      + "2. Multiply"
                                      + System.lineSeparator()
                                      + "3. Divide"
                                      + System.lineSeparator()
                                      + "4. Exit"
                                      + System.lineSeparator()));
    }

    @Test
    public void whenEngineerCalculatorInit() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bos);
        Calculator calculator = new EngineerCalculator(printStream::println);
        Input stubInput = new StubInput(new String[]{"8"});
        new CalculatorUI(stubInput, calculator, printStream::println).init();
        assertThat(bos.toString(), is("Menu."
                                    + System.lineSeparator()
                                    + "0. Add"
                                    + System.lineSeparator()
                                    + "1. Subtract"
                                    + System.lineSeparator()
                                    + "2. Multiply"
                                    + System.lineSeparator()
                                    + "3. Divide"
                                    + System.lineSeparator()
                                    + "4. Sinus"
                                    + System.lineSeparator()
                                    + "5. Cosinus"
                                    + System.lineSeparator()
                                    + "6. Tangens"
                                    + System.lineSeparator()
                                    + "7. Cotangens"
                                    + System.lineSeparator()
                                    + "8. Exit"
                                    + System.lineSeparator()));
    }
}