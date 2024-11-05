package ru.ssau.tk.fruktovi.sandboxoop.functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstantFunctionTest {
    @Test
    public void testConstantFunction() {
        ConstantFunction constantFunction = new ConstantFunction(5);
        assertEquals(5, constantFunction.apply(10), 0.001);
        assertEquals(5, constantFunction.apply(-1), 0.001);
        assertEquals(5, constantFunction.apply(0), 0.001);
    }

}
