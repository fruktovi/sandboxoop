package ru.ssau.tk.fruktovi.sandboxoop.functions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.BiFunction;
import org.junit.jupiter.api.Test;

public class RK_AlgorithmTest {

    private RK_Algorithm rungeKuttaFunction;

    @Test
    void testRungeKuttaMethod() {
        // Определяем дифференциальное уравнение: dy/dx = x * y
        BiFunction<Double, Double, Double> equation = (x, y) -> x * y;

        // Начальные условия
        double x0 = 0;
        double y0 = 1;


        RK_Algorithm rk = new RK_Algorithm(equation, x0, y0);


        double step = 1.0;
        double expectedValue = 1.645833333;


        assertEquals(expectedValue, rk.apply(step), 0.00001);
    }

    @Test
    void testRungeKuttaSolution2() {
        // dy/dx = x^2 + y
        BiFunction<Double, Double, Double> equation = (x, y) -> x * x + y;

        // Начальные условия
        double x0 = 0;
        double y0 = 1;

        RK_Algorithm rk = new RK_Algorithm(equation, x0, y0);


        double step = 1.0;
        double expectedValue = 3.145;


        assertEquals(expectedValue, rk.apply(step), 0.001);
    }
    @Test
    void testRungeKuttaSolution3() {
        // dy/dx = -y
        BiFunction<Double, Double, Double> equation = (x, y) -> -y;

        // Начальные условия
        double x0 = 0;
        double y0 = 5;

        RK_Algorithm rk = new RK_Algorithm(equation, x0, y0);


        double step = 1.0;
        double expectedValue = 1.875;


        assertEquals(expectedValue, rk.apply(step), 0.0001);
    }






}