package ru.job4j.generic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * simple wrapper to array
 * @param <T> - type of elements in array
 * @author mbardakov
 * @since 20.05.2020
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int size;

    public SimpleArray(int count) {
        array = new Object[count];
        size = 0;
    }

    /**
     * adds new element to array
     * @param model - element to add
     * @return - true if element added false if not enough capacity
     */
    public boolean add(T model) {
        var rsl = false;
        if (size < array.length) {
            array[size] = model;
            size++;
            rsl = true;
        }
        return rsl;
    }

    /**
     * set new element to the array cell
     * @param index - index of the cell
     * @param model - element to set
     */
    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        array[index] = model;
    }

    /**
     * gets element by index of the cell
     * @param index - index of the cell
     * @return - element
     */
    @SuppressWarnings("unchecked")
     public T get(int index) {
        Objects.checkIndex(index, size);
         return (T) array[index];
    }

    /**
     * removes element from the cell
     * @param index - index of the cell
     */
    public void remove(int index) {
        Objects.checkIndex(index, size);
        array[index] = null;
        leftShift(index);
    }

    /**
     * shifts array to the left
     * @param index index of erased element
     */
    private void leftShift(int index) {
        for (; index < size - 1; index++) {
            array[index] = array[index + 1];
            if (index == size - 2) {
                array[size - 1] = null;
                size--;
            }
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int pointer = 0;
            @Override
            public boolean hasNext() {
                return pointer < size;
            }

            @Override @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements");
                }
                return (T) array[pointer++];
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleArray<?> that = (SimpleArray<?>) o;
        return size == that.size
                && Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
}
