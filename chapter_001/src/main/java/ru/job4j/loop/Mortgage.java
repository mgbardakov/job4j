package ru.job4j.loop;

public class Mortgage {
    public int year(int amount, int salary, double percent) {
        int year = 0;
        double dAmount = amount;
        while(dAmount > 0){
            dAmount = (dAmount + dAmount * percent / 100) - salary;
            year++;
        }
        return year;
    }
}
