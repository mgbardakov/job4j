package ru.job4j.tracker;
import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void trackerAdd() {
        Item testItem = new Item("Сковорода");
        Tracker testTracker = new Tracker();
        assertThat(testTracker.add(testItem), is(testItem));
    }

    @Test
    public void trackerFindAll() {
        Tracker testTracker = new Tracker();
        Item pan = testTracker.add(new Item("Сковорода"));
        Item fork = testTracker.add(new Item("Вилка"));
        Item mug = testTracker.add(new Item("Кружка"));
        List<Item> result = new ArrayList<>();
        result.add(pan);
        result.add(fork);
        result.add(mug);
        assertThat(testTracker.findAll(), is(result));
    }

    @Test
    public void trackerFindByName() {
        Tracker testTracker = new Tracker();
        Item mug0 = testTracker.add(new Item("Кружка"));
        Item pan = testTracker.add(new Item("Сковорода"));
        Item fork = testTracker.add(new Item("Вилка"));
        Item mug = testTracker.add(new Item("Кружка"));
        List<Item> result = new ArrayList<>();
        result.add(mug0);
        result.add(mug);
        assertThat(testTracker.findByName("Кружка"), is(result));
    }

    @Test
    public void trackerFindByID() {
        Tracker testTracker = new Tracker();
        Item pan = testTracker.add(new Item("Сковорода"));
        Item fork = testTracker.add(new Item("Вилка"));
        Item mug = testTracker.add(new Item("Кружка"));
        assertThat(testTracker.findById(mug.getId()), is(mug));
    }

    @Test
    public void trackerFindByIDIfTheresNoOneThenNull() {
        Tracker testTracker = new Tracker();
        Item pan = testTracker.add(new Item("Сковорода"));
        Item fork = testTracker.add(new Item("Вилка"));
        Item mug = testTracker.add(new Item("Кружка"));
        assertThat(testTracker.findById("153215462"), nullValue());
    }

    @Test
    public void whenReplace() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id), is(nullValue()));
    }

    @Test
    public void whenNoDeleteItem() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = "123456789";
        assertThat(tracker.delete(id), is(false));
    }

    @Test
    public void whenNoReplaceItem() {
        Tracker tracker = new Tracker();
        String id = "123456789";
        Item bugWithDesc = new Item("Bug with description");
        assertThat(tracker.replace(id, bugWithDesc), is(false));
    }
}
