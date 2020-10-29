package ru.job4j.concurrent;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ForkJoinSearchTest {

    @Test
    public void whenFrankIs11() {
        User[] users = new User[]{User.of("Carlos"), User.of("Carlos"), User.of("Carlos"),
                User.of("Carlos"), User.of("Carlos"), User.of("Carlos"), User.of("Carlos"),
                User.of("Carlos"), User.of("Carlos"), User.of("Carlos"), User.of("Carlos"),
                User.of("Frank"), User.of("Carlos"), User.of("Carlos"), User.of("Carlos"),
                User.of("Carlos"), User.of("Carlos"), User.of("Carlos"), User.of("Carlos"),
                User.of("Carlos"), User.of("Carlos"), User.of("Carlos"), User.of("Carlos"),
                User.of("Carlos"), User.of("Carlos"), User.of("Carlos"), User.of("Carlos"),
                User.of("Carlos"), User.of("Carlos"), User.of("Carlos"), User.of("Carlos")};
        var rsl = ForkJoinSearch.search(users, x -> x.getName().equals("Frank"));
        assertThat(rsl, is(11));
    }
}