package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedContainerTest {
    @Test
    public void whenAddThenGet() {
        SimpleLinkedContainer<String> array = new SimpleLinkedContainer<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleLinkedContainer<String> array = new SimpleLinkedContainer<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleLinkedContainer<String> array = new SimpleLinkedContainer<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleLinkedContainer<String> array = new SimpleLinkedContainer<>();
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleLinkedContainer<String> array = new SimpleLinkedContainer<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleLinkedContainer<String> array = new SimpleLinkedContainer<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }

    @Test
    public void whenDeleteFirst() {
        SimpleLinkedContainer<String> array = new SimpleLinkedContainer<>();
        array.add("first");
        array.add("second");
        assertThat(array.delete(0), is("first"));
        assertThat(array.get(0), is("second"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenDeleteLast() {
        SimpleLinkedContainer<String> array = new SimpleLinkedContainer<>();
        array.add("first");
        array.add("second");
        array.add("third");
        assertThat(array.delete(2), is("third"));
        array.get(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenDeleteMiddle() {
        SimpleLinkedContainer<String> array = new SimpleLinkedContainer<>();
        array.add("first");
        array.add("second");
        array.add("third");
        assertThat(array.delete(1), is("second"));
        assertThat(array.get(0), is("first"));
        assertThat(array.get(1), is("third"));
        array.get(2);
    }
}
