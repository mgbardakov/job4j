package ru.job4j.templates;

import java.util.Map;

/**
 * @author mbardakov
 * @since 10.03.2020
 */
public interface Template {
    /**
     *
     * @param template - template
     * @param data - data to fill template
     * @return - filled template
     */
    String generate(String template, Map<String, String> data) throws Exception;
}
