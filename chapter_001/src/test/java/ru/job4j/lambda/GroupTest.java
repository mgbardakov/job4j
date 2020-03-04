package ru.job4j.lambda;

import java.util.*;

import org.junit.Test;

import java.util.List;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
public class GroupTest {
    List<Student> arg = List.of(new Student("Виктор", Set.of("Вязание", "Борьба", "Скрипка")),
            new Student("Павел", Set.of("Вязание", "Вышивание", "Оккультизм")),
            new Student("Сергей", Set.of("Моделирование", "Гадание", "Борьба")));

    @Test
    public void groupTest() {
            Map<String, Set<String>> expected = Map.of("Вязание", Set.of("Виктор", "Павел"),
                                                       "Борьба", Set.of("Виктор", "Сергей"),
                                                       "Скрипка", Set.of("Виктор"),
                                                       "Вышивание", Set.of("Павел"),
                                                       "Оккультизм", Set.of("Павел"),
            "Моделирование", Set.of("Сергей"),
            "Гадание", Set.of("Сергей"));
    Map<String, Set<String>> result = Group.sections(arg);
    assertThat(result, is(expected));
}
}
