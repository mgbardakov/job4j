package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenStub() {
        Input input = new StubInput(new String[]{"0"});
        StubAction stubAction = new StubAction();
        new StartUI().init(input, new Tracker(), new UserAction[]{stubAction});
        assertThat(stubAction.isCall(), is(true));
    }

}
