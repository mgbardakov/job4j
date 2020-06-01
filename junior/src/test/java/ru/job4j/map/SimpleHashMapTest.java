package ru.job4j.map;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void whenInsertOne1Two2ThenGet1And2() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        assertThat(map.insert("One", 1), is(true));
        assertThat(map.insert("Two", 2), is(true));
        assertThat(map.get("One"), is(1));
        assertThat(map.get("Two"), is(2));
    }

    @Test
    public void whenInsertOne1Two2ThenDeleteOne() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("One", 1);
        map.insert("Two", 2);
        map.delete("One");
        assertNull(map.get("One"));
    }

    @Test
    public void whenInsertOne1Two2Three2ThenGetIterator() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("One", 1);
        map.insert("Two", 2);
        map.insert("Three", 3);
        var it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(new SimpleHashMap.Entry<>("One", 1)));
        assertThat(it.next(), is(new SimpleHashMap.Entry<>("Two", 2)));
        assertThat(it.next(), is(new SimpleHashMap.Entry<>("Three", 3)));
    }

    @Test
    public void whenInsertKeyOneKeyTwoThenGetTwo() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("key", "one");
        map.insert("key", "two");
        assertThat(map.get("key"), is("two"));
    }
}