package ru.ssau.tk.fruktovi.sandboxoop.functions.factory;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.fruktovi.sandboxoop.functions.LinkedListTabulatedFunction;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionFactoryTest {
    @Test
    void testEqual(){
        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        assertInstanceOf(LinkedListTabulatedFunction.class, factory.create(new double[]{0, 1, 2}, new double[]{3, 4, 5}));
    }
}