package main.java.com.alexsid.lesson4;

import java.util.HashMap;
import java.util.Map;

public final class CalculatorProvider {

    private static Map<Class, MathBox.Calculator> calculators = new HashMap<>();

    static {
        calculators.put(Integer.class, new IntegerCalculator());
        calculators.put(Double.class, new DoubleCalculator());
        //ну и так далее
    }

    private CalculatorProvider() {
    }

    public static MathBox.Calculator getOne(Class<? extends Number> clazz) {
        return calculators.get(clazz);
    }

    private static final class IntegerCalculator implements MathBox.Calculator<Integer> {

        @Override
        public Integer add(Integer n1, Integer n2) {
            return n1 + n2;
        }

        @Override
        public Integer divide(Integer n, Integer divider) {
            return n / divider;
        }
    }

    private static final class DoubleCalculator implements MathBox.Calculator<Double> {

        @Override
        public Double add(Double n1, Double n2) {
            return n1 + n2;
        }

        @Override
        public Double divide(Double n, Double divider) {
            return n / divider;
        }
    }
}
