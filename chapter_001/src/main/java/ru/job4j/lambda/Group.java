package ru.job4j.lambda;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Group {

    public static Map<String, Set<String>> sections(List<Student> students) {
        return students.stream().flatMap(x -> x.getUnits().stream().map(u -> new Holder(u, x.getName()))).
                collect(Collectors.groupingBy(Holder::getKey, Collectors.mapping(Holder::getValue, Collectors.toSet())));
    }
    static class Holder {
        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        String key, value;

        Holder(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

}


