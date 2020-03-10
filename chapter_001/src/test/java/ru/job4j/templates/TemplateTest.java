package ru.job4j.templates;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TemplateTest {
  @Test
    public void whenTemplateFilledWithData() throws Exception {
      String expected = "We need a bucket in this room";
      String arg = "We need a ${name n} in this ${place}";
      Map<String, String> map = new HashMap<>(Map.of("name n", "bucket", "place", "room"));
      String result = new SimpleGenerator().generate(arg, map);
      assertThat(result, is(expected));
  }

    @Test (expected = NoRequiredKeyInData.class)
    public void whenNoSuchKeyInData() throws Exception {
        String arg = "We need a ${name} in this ${place}";
        Map<String, String> map = new HashMap<>(Map.of("name", "bucket"));
        new SimpleGenerator().generate(arg, map);
    }

    @Test (expected = NoRequiredKeyInTemplate.class)
    public void whenNoSuchKeyInTemplate() throws Exception {
        String arg = "We need a ${name} in this ${place}";
        Map<String, String> map = new HashMap<>(Map.of("name", "bucket", "place", "room", "space", "venus"));
        new SimpleGenerator().generate(arg, map);
    }
}