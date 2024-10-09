package ru.ssau.tk.fruktovi.sandboxoop.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrFunctionTest {
    @Test
    public void testApplyPositiveNumber() {
        MathFunction sqrFunction = new SqrFunction();
        assertEquals(4.0, sqrFunction.apply(2.0), 0.001);
        assertEquals(9.0, sqrFunction.apply(3.0), 0.001);
    }

}