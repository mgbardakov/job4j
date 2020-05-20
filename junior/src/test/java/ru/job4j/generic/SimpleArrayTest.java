package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    private SimpleArray<String> array;
    @Before
    public void doBefore() {
        array = new SimpleArray<>(6);
        array.add("One");
        array.add("Two");
        array.add("Three");
        array.add("Four");
        array.add("Five");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenAddedNewElement() {
        array.add("Six");
        assertThat(array.get(5), is("Six"));
        array.add("Seven");
        array.get(6);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenSetElement() {
        array.set(4, "FIVE");
        assertThat(array.get(4), is("FIVE"));
        array.set(5, "Six");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenRemoveExistingElement() {
        array.remove(3);
        assertThat(array.get(3), is("Five"));
        array.get(4);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenRemoveNonExistingElement() {
        array.remove(6);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetElements() {
        assertThat(array.get(3), is("Four"));
        array.get(6);
    }

    @Test
    public void whenIteratorNext() {
        Iterator<String> it = array.iterator();
        assertThat(it.next(), is("One"));
        assertThat(it.next(), is("Two"));
    }

    @Test
    public void whenIteratorNextIsOneAfterTwoHasNext() {
        Iterator<String> it = array.iterator();
        it.hasNext();
        it.hasNext();
        assertThat(it.next(), is("One"));
    }

    @Test
    public void whenIteratorNextFiveTimesThenIteratorHasNoNext() {
        Iterator<String> it = array.iterator();
        assertThat(it.next(), is("One"));
        assertThat(it.next(), is("Two"));
        assertThat(it.next(), is("Three"));
        assertThat(it.next(), is("Four"));
        assertThat(it.next(), is("Five"));
        assertThat(it.hasNext(), is(false));
    }

}