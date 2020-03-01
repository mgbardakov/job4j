package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixToList {
    public <T> List<T> matrixToList(T[][] arr) {
        return Arrays.stream(arr).flatMap(Arrays::stream).collect(Collectors.toList());
    }
}
