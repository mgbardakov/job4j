package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int result = 0;
        boolean breaked = false;
        int n = Math.min(o1.toCharArray().length, o2.toCharArray().length);
        int i = 0;
        while (i < n && !breaked) {
            if (o1.charAt(i) != (o2.charAt(i))) {
                result = Character.compare(o2.charAt(i), o1.charAt(i));
                breaked = true;
            }
            i++;
        }
        if (!breaked) {
            result = Integer.compare(o1.length(), o2.length());
        }
        return result;
    }
}