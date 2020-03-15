package ru.job4j.calculator;

import java.util.List;
import java.util.function.Consumer;

public class EngineerCalculator extends Calculator {
    private List<CalculateAction> actionList;

    public EngineerCalculator(Consumer<String> con) {
        super(con);
        this.actionList = super.getActionList();
        actionList.add(actionList.size() - 1, new SinusAction(con));
        actionList.add(actionList.size() - 1, new CosinusAction(con));
        actionList.add(actionList.size() - 1, new TangensAction(con));
        actionList.add(actionList.size() - 1, new CotangensAction(con));
    }

    public EngineerCalculator() {
        this(System.out::println);
    }

    @Override
    public List<CalculateAction> getActionList() {
        return actionList;
    }

    public double sin(double a) {
        return Math.sin(Math.toRadians(a));
    }

    public double cos(double a) {
        return Math.cos(Math.toRadians(a));
    }

    public double tg(double a) {
        return Math.tan(Math.toRadians(a));
    }

    public double ctg(double a) {
        return 1.0 / Math.tan(Math.toRadians(a));
    }
}
