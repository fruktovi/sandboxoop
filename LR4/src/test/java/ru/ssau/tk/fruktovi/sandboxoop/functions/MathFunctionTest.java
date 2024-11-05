package ru.ssau.tk.fruktovi.sandboxoop.functions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathFunctionTest {

    @Test
    public void testCompositeFunctions() {
        MathFunction add2 = new AdditionFunction(2);
        MathFunction multiply3 = new MultiplicationFunction(3);
        MathFunction multiply5 = new MultiplicationFunction(5);

        MathFunction compositeFunction = add2.andThen(multiply3).andThen(multiply5);

        double result = compositeFunction.apply(4); // (4 + 2) * 3 * 5 = 90
        assertEquals(90, result);
    }

    @Test
    public void testMultipleCompositions() {
        MathFunction add1 = new AdditionFunction(1);
        MathFunction add3 = new AdditionFunction(3);

        MathFunction CompositeFunction = add1.andThen(add3);

        double result = CompositeFunction.apply(4); // (4 + 1) + 3 = 8
        assertEquals(8, result);
    }

    @Test
    public void testIdentityFunction() {
        MathFunction identity = x -> x; // Лямбда для идентичной функции
        double result = identity.andThen(identity).apply(10); // 10
        assertEquals(10, result);
    }
}
