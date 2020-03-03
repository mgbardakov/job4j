package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class School {
    List<Student> collect(List<Student> students, Predicate<Student> predict) {
        return students.stream().filter(predict).collect(Collectors.toList());
    }
    public Map<String, Student> studentMap(List<Student> list) {
        return list.stream().distinct().collect(Collectors.toMap(Student::getSurname, x -> x));
    }

    public List<Student> levelOf(List<Student> students, int bound) {
        return  students.stream().
                sorted((Comparator.nullsLast(Comparator.naturalOrder()))).
                flatMap(Stream::ofNullable).takeWhile(x -> x.getScore() > bound).
                collect(Collectors.toList());
    }
}
