package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    Store<Role> store = new RoleStore();

    @Before
    public void doBefore() {
        store.add(new Role("1", "Gardener"));
        store.add(new Role("2", "Swimmer"));
        store.add(new Role("3", "Lawyer"));
        store.add(new Role("4", "Doctor"));
        store.add(new Role("5", "Proger"));
    }

    @Test
    public void whenFindRoleById2ThenTitleIsSwimmer() {
        assertThat(store.findById("2").getTitle(), is("Swimmer"));
    }

    @Test
    public void whenDeleteRoleById5ThenNullAfterCalling() {
        assertThat(store.delete("5"), is(true));
        assertNull(store.findById("5"));
    }

    @Test
    public void whenReplaceSwimmerWithRunner() {
        assertThat(store.replace("2", new Role("2", "Runner")), is(true));
        assertThat(store.findById("2").getTitle(), is("Runner"));
    }

    @Test
    public void whenAddNewUserId6NameTerence() {
        store.add(new Role("6", "Driver"));
        assertThat(store.findById("6").getTitle(), is("Driver"));
    }

}