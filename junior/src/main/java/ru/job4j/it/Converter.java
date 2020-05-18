package ru.job4j.it;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * class that flats iterator of iterators
 * @author mbardakov
 * @since 18.05.2020
 */
public class Converter {
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            private Iterator<Integer> currentIterator = Collections.emptyIterator();

            /**
             * returns if iterator has next element and skips empty iterators
             * @return next value exists / don't exist
             */
            @Override
            public boolean hasNext() {
                while (!currentIterator.hasNext() && it.hasNext()) {
                    currentIterator = it.next();
                }
                return currentIterator.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return currentIterator.next();
            }

        };
    }
}