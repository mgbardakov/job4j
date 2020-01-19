package ru.job4j.oop;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DummyDicTest {

    @Test
    public void whenRubber() {
        DummyDic testDic = new DummyDic();
        String testString = testDic.engToRus("rubber");
        assertThat(testString, is("Неизвестное слово. rubber"));
    }
}
