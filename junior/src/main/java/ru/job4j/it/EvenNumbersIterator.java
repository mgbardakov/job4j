package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * class iterator for even numbers
 * @author mbardakov
 * @since 18.05.2020
 */
public class EvenNumbersIterator implements Iterator<Integer> {

    private int pointer;
    private final int[] numbers;

    public EvenNumbersIterator(int[] numbers) {
        this.numbers = numbers;
    }


    @Override
    public boolean hasNext() {
        return hasNextEven();
    }

    @Override
    public Integer next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("There's no more even numbers");
        } else {
            return this.numbers[pointer++];
        }
    }

    /**
     * check if there is nex even number and changes pointer position
     * @return even number exists / not exist
     */
    private boolean hasNextEven() {
        boolean rsl = false;
        for (int index = pointer; index < this.numbers.length; index++) {
            if (numbers[index] % 2 == 0) {
                pointer = index;
                rsl = true;
                break;
            }
        }
        return rsl;
    }
}
