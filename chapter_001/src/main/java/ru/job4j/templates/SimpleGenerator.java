package ru.job4j.templates;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mbardakov
 * @since 11.03.2020
 */
public class SimpleGenerator implements Template {
    private final Pattern keys = Pattern.compile("(\\$\\{.*?})");
    @Override
    public String generate(String template, Map<String, String> data) throws Exception {
        var result = template;
            Matcher matcher = keys.matcher(template);
            String key;
            while (matcher.find()) {
                var rawKey = matcher.group();
                key = rawKey.substring(2, rawKey.length() - 1);
                if (!data.containsKey(key)) {
                    throw new NoRequiredKeyInData();
                }
                var sub = matcher.end() <= result.length() - 1 ? template.substring(matcher.start(), matcher.end())
                        : template.substring(matcher.start());
                var replacement = data.get(key);
                data.remove(key);
                result = result.replace(sub, replacement);
            }
            if (!data.isEmpty()) {
                throw new NoRequiredKeyInTemplate();
            }
        return result;
    }
}
