package ru.ssau.tk.fruktovi.sandboxoop.functions;

import ru.ssau.tk.fruktovi.sandboxoop.functions.AbstractTabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.fruktovi.sandboxoop.exceptions.DifferentLengthOfArraysException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractTabulatedFunctionTest {

    @Test
    public void testCheckLengthIsTheSame() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {1.0, 2.0};

        assertThrows(DifferentLengthOfArraysException.class, () -> {
            AbstractTabulatedFunction.checkLengthIsTheSame(xValues, yValues);
        });
    }

    @Test
    public void testCheckSorted() {
        double[] xValues = {1.0, 3.0, 2.0};

        assertThrows(ArrayIsNotSortedException.class, () -> {
            AbstractTabulatedFunction.checkSorted(xValues);
        });
    }
}