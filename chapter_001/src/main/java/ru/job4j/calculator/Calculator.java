package ru.job4j.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Calculator класс для выполнения арифметических вычислений
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 8.01.2020
 * @version 1.0
 */
public class Calculator {

    private List<CalculateAction> actionList;

    public Calculator(Consumer<String> con) {
        this.actionList = new ArrayList<>();
        actionList.add(new AddAction(con));
        actionList.add(new SubtractAction(con));
        actionList.add(new MultiplyAction(con));
        actionList.add(new DivideAction(con));
        actionList.add(new ExitAction());
    }

    public Calculator() {
        this(System.out::println);
    }

    public List<CalculateAction> getActionList() {
        return actionList;
    }

    /**
     * Сложение
     * @param first первое слагаемое
     * @param second второе слагаемое
     */
    public double add(double first, double second) {
        return first + second;
    }

    /**
     * Деление
     * @param first делимое
     * @param second делитель
     */
    public double div(double first, double second) {
        return first / second;
    }

    /**
     * Умножение
     * @param first первый множитель
     * @param second второй множитель
     */
    public double multiply(double first, double second) {
        return first * second;
    }

    /**
     * Вычитание
     * @param first уменьшаемое
     * @param second вычитаемое
     */
    public double subtract(double first, double second) {
        return first - second;
    }

}
