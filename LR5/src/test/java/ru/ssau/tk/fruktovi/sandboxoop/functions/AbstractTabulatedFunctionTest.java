package ru.ssau.tk.fruktovi.sandboxoop.functions;

import ru.ssau.tk.fruktovi.sandboxoop.functions.AbstractTabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.fruktovi.sandboxoop.exceptions.DifferentLengthOfArraysException;

import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;
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

    @Test
    void testToString(){
        double[] xValues = new double[]{1.0, 2.0, 3.0};
        double[] yValues = new double[]{4.0, 5.0, 6.0};
        AbstractTabulatedFunction function1 = new LinkedListTabulatedFunction (xValues, yValues);

        String stringLinkedList = "LinkedListTabulatedFunction size = 3\n[1.0; 4.0]\n[2.0; 5.0]\n[3.0; 6.0]\n";

        assertEquals(stringLinkedList, function1.toString());
    }
}