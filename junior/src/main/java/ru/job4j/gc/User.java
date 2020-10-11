package ru.job4j.gc;

public class User {
    long id;
    long age;

    public User(long id, long age) {
        this.id = id;
        this.age = age;
    }

    @SuppressWarnings("deprecation")@Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("user deleted");
    }
}
