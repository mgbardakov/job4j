package ru.job4j.tracker;

import org.junit.Test;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class HbmTrackerTest {

    @Test
    public void whenAddItemAndFindItByID() {
        var expectedItem = new Item("item", "description");
        Store tracker = new HbmTracker();
        tracker.init();
        tracker.add(expectedItem);
        assertThat(tracker.findById("1"), is(expectedItem));
    }

    @Test
    public void whenAddItemReplaceItAndFindItByID() {
        var item = new Item("item", "description");
        Store tracker = new HbmTracker();
        tracker.init();
        tracker.add(item);
        tracker.replace("1", new Item("changedName", "changedDesc"));
        var rslItem = tracker.findById("1");
        assertEquals("changedName", rslItem.getName());
        assertEquals("changedDesc", rslItem.getDescription());
    }

    @Test
    public void whenAddItemAndThenDeleteIt() {
        var item = new Item("item", "description");
        Store tracker = new HbmTracker();
        tracker.init();
        tracker.add(item);
        tracker.delete("1");
        assertNull(tracker.findById("1"));
    }

    @Test
    public void whenAdd3ItemsAndThenFindAll() {
        var expectedList = List.of(new Item(1, "item1"),
                                             new Item(2, "item2"),
                                             new Item(3, "item3"));
        Store tracker = new HbmTracker();
        tracker.init();
        tracker.add(new Item("item1"));
        tracker.add(new Item("item2"));
        tracker.add(new Item("item3"));
        assertEquals(expectedList, tracker.findAll());
    }

    @Test
    public void whenAddItemAndFindItByName() {
        var item = new Item("item", "description");
        Store tracker = new HbmTracker();
        tracker.init();
        tracker.add(item);
        assertEquals(item, tracker.findByName(item.getName()).get(0));
    }
}