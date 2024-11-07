package ru.ssau.tk.fruktovi.sandboxoop.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.fruktovi.sandboxoop.functions.MathFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.SqrFunction;

import static org.junit.jupiter.api.Assertions.*;

public class MiddleSteppingDifferentialOperatorTest {
    @Test
    public void testDerivativeOfSqrFunction() {
        MathFunction sqrFunction = new SqrFunction();

        double step = 1e-5;
        MiddleSteppingDifferentialOperator operator = new MiddleSteppingDifferentialOperator(step);
        MathFunction derivative = operator.derive(sqrFunction);


        assertEquals(0.0, derivative.apply(0.0), 1e-3);

        assertEquals(2.0, derivative.apply(1.0), 1e-3);

        assertEquals(4.0, derivative.apply(2.0), 1e-3);

        assertEquals(-2.0, derivative.apply(-1.0), 1e-3);

        assertEquals(-4.0, derivative.apply(-2.0), 1e-3);
    }
}