package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result = 0;
        boolean breaked = false;
        int n = Math.min(left.length(), right.length());
        int i = 0;
        while (i < n && !breaked) {
            if (left.charAt(i) != right.charAt(i)) {
                result = Character.compare(left.charAt(i), right.charAt(i));
                breaked = true;
            }
            i++;
        }
        if (!breaked) {
            result = Integer.compare(left.length(), right.length());
        }
        return result;
    }
}
