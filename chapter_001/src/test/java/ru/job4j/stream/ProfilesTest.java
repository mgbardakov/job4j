package ru.job4j.stream;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
public class ProfilesTest {
    @Test
    public void collectTest() {
        List<Profile> arg = Arrays.asList(new Profile(new Address("Gotham", "Pine Street", 13, 87)),
                new Profile(new Address("Gotham", "Pine Street", 13, 87)),
                new Profile(new Address("Chicago", "Oak Street", 12, 80)),
                new Profile(new Address("Helsinki", "Spruce Street", 10, 5)));
        List<Address> expected = Arrays.asList(new Address("Chicago", "Oak Street", 12, 80),
                                          new Address("Gotham", "Pine Street", 13, 87),
                                          new Address("Helsinki", "Spruce Street", 10, 5));
        List<Address> actual = Profiles.collect(arg);
        assertThat(expected, is(actual));
    }
}
