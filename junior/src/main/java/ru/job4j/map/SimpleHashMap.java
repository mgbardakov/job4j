package ru.job4j.map;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * simple hash map class
 * @param <K> - key type
 * @param <V> - value type
 * @author mbardakov
 * @since 01.06.2020
 */
public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Entry<K, V>> {
    private Object[] array;
    private double loadCriteria;
    private int size = 0;
    private int modCount = 0;

    public SimpleHashMap() {
        this.array = new Object[16];
        this.loadCriteria = 0.75;
    }

    /**
     * adds new pair to the map
     * @param key - key
     * @param value - value
     * @return success/failure
     */
    @SuppressWarnings("unchecked")
    public boolean insert(K key, V value) {
        if (size * 1.0 / array.length > loadCriteria) {
            enlargeSize();
        }
        var index = getBucketIndex(key, array.length);
        var rsl = false;
        var node = (Entry<K, V>) array[index];
        if (node == null || node.key.equals(key)) {
            array[index] = new Entry<>(key, value);
            rsl = true;
            size++;
            modCount++;
        }
        return rsl;
    }

    /**
     * gets value by key
     * @param key - key
     * @return value
     */
    @SuppressWarnings("unchecked")
    public V get(K key) {
        var index = getBucketIndex(key, array.length);
        Entry<K, V> node = (Entry<K, V>) array[index];
        if (node != null && node.key.equals(key)) {
            return node.value;
        } else {
            return null;
        }
    }

    /**
     * deletes element by key
     * @param key - key
     * @return success/failure
     */
    @SuppressWarnings("unchecked")
    public boolean delete(K key) {
        var index = getBucketIndex(key, array.length);
        var rsl = false;
        Entry<K, V> node = (Entry<K, V>) array[index];
        if (node != null && node.key.equals(key)) {
            array[index] = null;
            size--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    /**
     * gets bucket index by key hash value
     * @param key - key
     * @param length - current bucket array length
     * @return bucket index
     */
    private int getBucketIndex(K key, int length) {
        var hash = key == null ? 0 : key.hashCode() ^ key.hashCode() >>> 16;
        return hash & (length - 1);
    }

    /**
     * enlarges size of the bucket array
     * and puts all previously added elements into it
     */
    @SuppressWarnings("unchecked")
    private void enlargeSize() {
        var newArray = new Object[size * 2];
        for (Object o : this) {
            var node = (Entry<K, V>) o;
            var index = getBucketIndex(node.key, newArray.length);
            newArray[index] = node;
        }
        array = newArray;
    }

    /**
     * gets Entry iterator
     * @return entry iterator
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            int pointer = 0;
            int localModCount = modCount;
            @Override
            public boolean hasNext() {
                boolean rsl = false;
                for (int index = pointer; index < array.length; index++) {
                    if (array[index] != null) {
                        pointer = index;
                        rsl = true;
                        break;
                    }
                }
                return rsl;
            }

            @Override @SuppressWarnings("unchecked")
            public Entry<K, V> next() {
                if (localModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("There's no more even numbers");
                } else {
                    return (Entry<K, V>) array[pointer++];
                }
            }
        };
    }

    public static class Entry<K, T> {
        private K key;
        private T value;

        public Entry(K key, T value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Entry<?, ?> node = (Entry<?, ?>) o;
            return Objects.equals(key, node.key)
                    && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
