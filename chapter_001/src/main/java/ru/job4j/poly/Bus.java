package ru.job4j.poly;

public class Bus implements Transport {
    private int passengers;
    private double fuelPrice;

    public Bus(double fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    @Override
    public void drive() {
        System.out.println("Whroom-whroom");
    }

    @Override
    public void passengers(int count) {
      this.passengers = count;
    }

    @Override
    public double fill(int count) {
        return count * fuelPrice;
    }
}
