package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    Store<User> store = new UserStore();

    @Before
    public void doBefore() {
        store.add(new User("1", "Mitya"));
        store.add(new User("2", "Gosha"));
        store.add(new User("3", "Arkasha"));
        store.add(new User("4", "Zhora"));
        store.add(new User("5", "Tolik"));
    }

    @Test
    public void whenFindUserById2ThenNameIsGosha() {
        assertThat(store.findById("2").getName(), is("Gosha"));
    }

    @Test
    public void whenDeleteUserById5ThenNullAfterCalling() {
        assertThat(store.delete("5"), is(true));
        assertNull(store.findById("5"));
    }

    @Test
    public void whenReplaceGoshaWithValya() {
        assertThat(store.replace("2", new User("2", "Valya")), is(true));
        assertThat(store.findById("2").getName(), is("Valya"));
    }

    @Test
    public void whenAddNewUserId6NameTerence() {
        store.add(new User("6", "Terence"));
        assertThat(store.findById("6").getName(), is("Terence"));
    }
}