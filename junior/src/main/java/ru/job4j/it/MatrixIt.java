package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * class iterator for matrices
 * @author mbardakov
 * @since 18.05.2020
 */
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return hasNextValid();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("There's no more elements");
        }
        var rsl = data[row][column++];
        if (data[row].length == column) {
            column = 0;
            row++;
        }
        return rsl;
    }

    /**
     *
     * @param row current row
     * @param column current column
     * @return if current element is valid
     */
    private boolean isValid(int row, int column) {
        return  row < data.length && column < data[row].length;
    }

    /**
     * method searches for the next valid element
     * and increments row pointer
     * @return element exists / not exist
     */
    private boolean hasNextValid() {
        while (!isValid(row, column) && row < data.length) {
            row++;
        }
        return  isValid(row, column);
    }
}
