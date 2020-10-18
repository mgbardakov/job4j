package ru.job4j.concurrent.storage;


public class User {
    private int id;
    private int amount;

    public User(int id, int amount) {
        this.amount = amount;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
