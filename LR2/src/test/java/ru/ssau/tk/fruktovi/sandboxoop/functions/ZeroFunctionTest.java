package ru.ssau.tk.fruktovi.sandboxoop.functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ZeroFunctionTest {
    @Test
    public void testZeroFunction() {
        ZeroFunction zeroFunction = new ZeroFunction();
        assertEquals(0, zeroFunction.apply(10), 0.001);
        assertEquals(0, zeroFunction.apply(-1), 0.001);
        assertEquals(0, zeroFunction.apply(0), 0.001);
    }

}