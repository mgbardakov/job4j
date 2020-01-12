package ru.job4j.calculator;

/**
 * Calculator класс для выполнения арифметических вычислений
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 8.01.2020
 * @version 1.0
 */
public class Calculator {

    /**
     * Сложение
     * @param first первое слагаемое
     * @param second второе слагаемое
     */
    public static void add(double first, double second) {
        double result =  first + second;
        System.out.println(first + " + " + second + " = " + result);
    }

    /**
     * Деление
     * @param first делимое
     * @param second делитель
     */
    public static void div(double first, double second) {
        double result =  first / second;
        System.out.println(first + " / " + second + " = " + result);
    }

    /**
     * Умножение
     * @param first первый множитель
     * @param second второй множитель
     */
    public static void multiply(double first, double second) {
        double result =  first * second;
        System.out.println(first + " * " + second + " = " + result);
    }

    /**
     * Вычитание
     * @param first уменьшаемое
     * @param second вычитаемое
     */
    public static void subtract(double first, double second) {
        double result =  first - second;
        System.out.println(first + " - " + second + " = " + result);
    }

    /**
     * Умножение
     * @param args - args
     */
    public static void main(String[] args) {
        add(1, 1);
        div(4, 2);
        multiply(2,1);
        subtract(10,5);
    }
}
