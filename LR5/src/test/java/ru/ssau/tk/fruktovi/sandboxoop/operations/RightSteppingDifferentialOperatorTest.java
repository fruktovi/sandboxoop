package ru.ssau.tk.fruktovi.sandboxoop.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.fruktovi.sandboxoop.functions.MathFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.SqrFunction;

import static org.junit.jupiter.api.Assertions.*;

public class RightSteppingDifferentialOperatorTest {

    @Test
    public void testDerivativeOfSqrFunction() {
        MathFunction sqrFunction = new SqrFunction();
        double step = 1e-5;
        RightSteppingDifferentialOperator operator = new RightSteppingDifferentialOperator(step);

        MathFunction derivative = operator.derive(sqrFunction);

        assertEquals(0.0, derivative.apply(0.0), 1e-3);

        assertEquals(2.0, derivative.apply(1.0), 1e-3);

        assertEquals(4.0, derivative.apply(2.0), 1e-3);

        assertEquals(-2.0, derivative.apply(-1.0), 1e-3);

        assertEquals(100.0, derivative.apply(50.0), 1e-3);
    }
}