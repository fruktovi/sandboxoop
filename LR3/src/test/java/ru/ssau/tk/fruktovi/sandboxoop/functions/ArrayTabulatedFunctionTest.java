package ru.ssau.tk.fruktovi.sandboxoop.functions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayTabulatedFunctionTest {

    private double[] xValues;
    private double[] yValues;
    private ArrayTabulatedFunction function;

    @BeforeEach
    public void setUp() {
        xValues = new double[]{1.0, 2.0, 3.0};
        yValues = new double[]{1.0, 4.0, 9.0};
        function = new ArrayTabulatedFunction(xValues, yValues);
    }



    @Test
    public void testConstructorWithMathFunction() {
        MathFunction source = (x) -> x * x;
        ArrayTabulatedFunction functionFromMath = new ArrayTabulatedFunction(source, 1.0, 3.0, 3);

        assertEquals(1.0, functionFromMath.getX(0));
        assertEquals(4.0, functionFromMath.getY(1));
        assertEquals(9.0, functionFromMath.getY(2));
    }

    @Test
    public void testGetCount() {
        assertEquals(3, function.getCount());
    }

    @Test
    public void testGetX() {
        assertEquals(1.0, function.getX(0));
        assertEquals(2.0, function.getX(1));
        assertEquals(3.0, function.getX(2));
    }

    @Test
    public void testGetY() {
        assertEquals(1.0, function.getY(0));
        assertEquals(4.0, function.getY(1));
        assertEquals(9.0, function.getY(2));
    }

    @Test
    public void testSetY() {
        function.setY(1, 5.0);
        assertEquals(5.0, function.getY(1));
    }

    @Test
    public void testLeftBound() {
        assertEquals(1.0, function.leftBound());
    }

    @Test
    public void testRightBound() {
        assertEquals(3.0, function.rightBound());
    }

    @Test
    public void testIndexOfX() {
        assertEquals(1, function.indexOfX(2.0));
        assertEquals(-1, function.indexOfX(4.0));
    }

    @Test
    public void testIndexOfY() {
        assertEquals(1, function.indexOfY(4.0));
        assertEquals(-1, function.indexOfY(5.0));
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(0, function.floorIndexOfX(1.0));
        assertEquals(1, function.floorIndexOfX(2.5));
        assertEquals(2, function.floorIndexOfX(3.0));
        assertEquals(2, function.floorIndexOfX(4.0));
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(1.0, function.extrapolateLeft(0.0));
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(9.0, function.extrapolateRight(4.0));
    }

    @Test
    public void testInterpolate() {
        double interpolatedValue = function.interpolate(2.5, 1);
        assertEquals(6.5, interpolatedValue, 0.01);
    }

    @Test
    public void testGetXInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> function.getX(3));
    }

    @Test
    public void testGetYInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> function.getY(3));
    }

    @Test
    public void testSetYInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> function.setY(3, 4.0));
    }
}