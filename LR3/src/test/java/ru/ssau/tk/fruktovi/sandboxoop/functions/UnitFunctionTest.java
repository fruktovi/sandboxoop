package ru.ssau.tk.fruktovi.sandboxoop.functions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UnitFunctionTest {
    @Test
    public void testUnitFunction() {
        UnitFunction unitFunction = new UnitFunction();
        assertEquals(1, unitFunction.apply(10), 0.001);
        assertEquals(1, unitFunction.apply(-1), 0.001);
        assertEquals(1, unitFunction.apply(0), 0.001);
    }

}