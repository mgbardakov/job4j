package ru.job4j.tracker;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Store store = new SqlTracker();
        store.init();
        Item item = new Item("test1");
        store.add(item);
        Item result = store.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
        store.delete(result.getId());
    }

    @Test
    public void trackerAdd() {
        Item testItem = new Item("Сковорода");
        Store testStore = new SqlTracker();
        testStore.init();
        assertThat(testStore.add(testItem), is(testItem));
        testStore.delete(testItem.getId());
    }

    @Test
    public void trackerFindAll() {
        Store testStore = new SqlTracker();
        testStore.init();
        Item pan = testStore.add(new Item("Сковорода"));
        Item fork = testStore.add(new Item("Вилка"));
        Item mug = testStore.add(new Item("Кружка"));
        List<Item> result = new ArrayList<>();
        result.add(pan);
        result.add(fork);
        result.add(mug);
        assertThat(testStore.findAll(), is(result));
        testStore.delete(pan.getId());
        testStore.delete(fork.getId());
        testStore.delete(mug.getId());
    }

    @Test
    public void trackerFindByName() {
        Store testStore = new SqlTracker();
        testStore.init();
        Item mug0 = testStore.add(new Item("Кружка"));
        Item pan = testStore.add(new Item("Сковорода"));
        Item fork = testStore.add(new Item("Вилка"));
        Item mug = testStore.add(new Item("Кружка"));
        List<Item> result = new ArrayList<>();
        result.add(mug0);
        result.add(mug);
        assertThat(testStore.findByName("Кружка"), is(result));
        testStore.delete(mug0.getId());
        testStore.delete(pan.getId());
        testStore.delete(fork.getId());
        testStore.delete(mug.getId());
    }

    @Test
    public void trackerFindByID() {
        Store testStore = new SqlTracker();
        testStore.init();
        Item pan = testStore.add(new Item("Сковорода"));
        Item fork = testStore.add(new Item("Вилка"));
        Item mug = testStore.add(new Item("Кружка"));
        assertThat(testStore.findById(mug.getId()), is(mug));
        testStore.delete(pan.getId());
        testStore.delete(fork.getId());
        testStore.delete(mug.getId());
    }

    @Test
    public void trackerFindByIDIfTheresNoOneThenNull() {
        Store testStore = new SqlTracker();
        testStore.init();
        Item pan = testStore.add(new Item("Сковорода"));
        Item fork = testStore.add(new Item("Вилка"));
        Item mug = testStore.add(new Item("Кружка"));
        assertThat(testStore.findById("153215462"), nullValue());
        testStore.delete(pan.getId());
        testStore.delete(fork.getId());
        testStore.delete(mug.getId());
    }

    @Test
    public void whenReplace() {
        Store store = new SqlTracker();
        store.init();
        Item bug = new Item("Bug");
        store.add(bug);
        String id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        store.replace(id, bugWithDesc);
        assertThat(store.findById(id).getName(), is("Bug with description"));
        store.delete(bug.getId());
    }

    @Test
    public void whenDelete() {
        Store store = new SqlTracker();
        store.init();
        Item bug = new Item("Bug");
        store.add(bug);
        String id = bug.getId();
        store.delete(id);
        assertThat(store.findById(id), is(nullValue()));
    }

    @Test
    public void whenNoDeleteItem() {
        Store store = new SqlTracker();
        store.init();
        Item bug = new Item("Bug");
        store.add(bug);
        String id = "123456789";
        assertThat(store.delete(id), is(false));
        store.delete(bug.getId());
    }

    @Test
    public void whenNoReplaceItem() {
        Store store = new SqlTracker();
        store.init();
        String id = "123456789";
        Item bugWithDesc = new Item("Bug with description");
        assertThat(store.replace(id, bugWithDesc), is(false));
    }
}
