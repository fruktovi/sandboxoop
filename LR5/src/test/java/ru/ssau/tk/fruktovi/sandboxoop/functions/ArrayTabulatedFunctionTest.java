package ru.ssau.tk.fruktovi.sandboxoop.functions;

import static org.junit.jupiter.api.Assertions.*;
import ru.ssau.tk.fruktovi.sandboxoop.exceptions.InterpolationException;
import ru.ssau.tk.fruktovi.sandboxoop.exceptions.DifferentLengthOfArraysException;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.NoSuchElementException;
import java.util.Iterator;


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
    void testConstructorDifferentLengthOfArraysException() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0};

        assertThrows(DifferentLengthOfArraysException.class,
                () -> new ArrayTabulatedFunction(xValues, yValues));
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

    @Test
    public void testToString() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {2, 4, 6};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals("ArrayTabulatedFunction:\nx: 1.0, y: 2.0\nx: 2.0, y: 4.0\nx: 3.0, y: 6.0\n", function.toString());
    }

    @Test
    public void testEquals() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {2, 4, 6};
        ArrayTabulatedFunction function1 = new ArrayTabulatedFunction(xValues, yValues);
        ArrayTabulatedFunction function2 = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(function1, function2);
    }
    @Test
    public void testHashCode() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {2, 4, 6};
        ArrayTabulatedFunction function1 = new ArrayTabulatedFunction(xValues, yValues);
        ArrayTabulatedFunction function2 = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(function1.hashCode(), function2.hashCode());
    }

    @Test
    public void testClone() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        ArrayTabulatedFunction clonedFunction = function.clone();

        assertEquals(function, clonedFunction);
        assertNotSame(function, clonedFunction);
    }
    @Test
    public void testInterpolateThrowsInterpolationException() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {3.0, 2.0, 1.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        double xOutOfRange = 0.5;

        assertThrows(InterpolationException.class, () -> {
            function.interpolate(xOutOfRange, 0);
        });

        double xOutOfRangeHigh = 3.5;
        assertThrows(InterpolationException.class, () -> {
            function.interpolate(xOutOfRangeHigh, 1);
        });
    }
    
    @Test
    public void testIterator() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        Iterator<Point> iterator = function.iterator();

        int index = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(xValues[index], point.x);
            assertEquals(yValues[index], point.y);
            index++;
        }
        assertEquals(xValues.length, index);
    }

    @Test
    public void testIteratorNoSuchElementException() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        Iterator<Point> iterator = function.iterator();

        while (iterator.hasNext()) {
            iterator.next();
        }

        assertThrows(NoSuchElementException.class, iterator::next);
    }

}