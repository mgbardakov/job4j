package ru.job4j.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        for (Integer key : users.keySet()) {
            result.add(User.of(users.get(key).getName()));
        }
        return result;
    }
}
