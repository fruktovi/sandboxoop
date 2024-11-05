package ru.ssau.tk.fruktovi.sandboxoop.functions;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.ssau.tk.fruktovi.sandboxoop.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.fruktovi.sandboxoop.exceptions.ArrayIsNotSortedException;

class LinkedListTabulatedFunctionTest {

    @Test
    void testFloorIndexOfXThrowsIllegalStateException() {
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction();
        // Test when head is null (list is empty)
        assertThrows(IllegalStateException.class, () -> {
            test.floorIndexOfX(5.0);
        });
    }

    @Test
    void testFloorIndexOfXThrowsIllegalArgumentException() {
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction();
        // Initialize the list with some values for testing bounds
        test.insert(3.0, 9.0);
        test.insert(5.0, 25.0);

        // Test when x is less than the left bound (head.x)
        assertThrows(IllegalArgumentException.class, () -> {
            test.floorIndexOfX(2.0);
        });
    }

    @Test
    void testFloorIndexOfXValidIndex() {
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction();
        // Insert elements in the list to test normal functionality
        test.insert(1.0, 2.0);
        test.insert(2.0, 4.0);
        test.insert(3.0, 6.0);

        // Test when x is in a valid range
        assertEquals(1, test.floorIndexOfX(2.5));
        assertEquals(0, test.floorIndexOfX(1.5));
    }

    @Test
    void extrapolateLeft() {
        double[] x = {1, 2, 2.5, 10, 11};
        double[] y = {2, 4, 3, 4,-5};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(x,y);
        Assertions.assertEquals(-2, test.extrapolateLeft(-1));
    }

    @Test
    void extrapolateRight() {
        double[] x = {0, 1, 2, 3, 4, 5};
        double[] y = {0, 2, 1, 2, 16, 20};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(x,y);
        Assertions.assertEquals(24, test.extrapolateRight(6));
    }

    @Test
    void testInterpolate(){

        double[] xArray = {1.0, 2.0, 4.5, 10.0};
        double[] yArray = {0.0, 3.0, 2.0, 1.1};
        LinkedListTabulatedFunction linkedList = new LinkedListTabulatedFunction(xArray, yArray);

        assertEquals(1.26, linkedList.apply(9.0), 0.01);
        assertEquals(2.25, linkedList.apply(3.0), 0.01);
    }

    @Test
    void getCount() {
        MathFunction func = new SqrFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,10,10);
        Assertions.assertEquals(10, test.getCount());
    }

    @Test
    void getX() {
        MathFunction func = new SqrFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,10);
        Assertions.assertEquals(0.0, test.getX(9), 0.001);
    }

    @Test
    void getY() {
        MathFunction func = new SqrFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,9);
        Assertions.assertEquals(0.0, test.getY(8));
    }

    @Test
    void setY() {
        MathFunction func = new SqrFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,9);
        test.setY(2,10);
        Assertions.assertEquals(10, test.getY(2));
    }

    @Test
    void indexOfX() {
        double[] x = {1, 1.5, 2.5, 10, 11};
        double[] y = {2, 2, 3, 4,-5};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(x,y);
        Assertions.assertEquals(1, test.indexOfX(1.5));
    }

    @Test
    void indexOfY() {
        MathFunction func = new IdentityFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,6);
        Assertions.assertEquals(1, test.indexOfY(1));
    }

    @Test
    void leftBound() {
        MathFunction func = new IdentityFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,5);
        Assertions.assertEquals(0, test.leftBound());
    }

    @Test
    void rightBound() {
        MathFunction func = new IdentityFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,5);
        Assertions.assertEquals(5, test.rightBound());
    }
    @Test
    void remove1() {
        double[] x = {1, 2, 5, 10 ,11};
        double[] y = {2, 4, 3, 4, -4};
        int index = 2;
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(x, y);
        double removed_obj = test.getX(index+1);
        test.remove(index);
        Assertions.assertEquals(removed_obj, test.getX(index));
    }

    @Test
    void remove2() {
        double[] x = {1, 2, 5, 10 ,11};
        double[] y = {2, 4, 3, 4, -4};
        int index = 0;
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(x, y);
        double obj = test.getX(index+1);
        test.remove(index);
        Assertions.assertEquals(obj, test.getX(index));
    }
    @Test
    void insert1() {
        MathFunction func = new IdentityFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,6);
        test.insert(2.5, 22222);
        Assertions.assertEquals(3.0, test.getY(3));
    }
    @Test
    void insert2() {
        MathFunction func = new IdentityFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,6);
        test.insert(-1, 22222);
        Assertions.assertEquals(-1, test.getX(0));
    }
    @Test
    void insert3() {
        MathFunction func = new IdentityFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,6);
        test.insert(6, 22222);
        Assertions.assertEquals(22222, test.getY(6));
    }
    @Test
    public void testToString() {
        double[] xArray = {1.0, 2.0, 4.5, 10.0};
        double[] yArray = {0.0, 3.0, 2.0, 1.1};
        LinkedListTabulatedFunction linkedList = new LinkedListTabulatedFunction(xArray, yArray);

        String expectedString = "(1.0; 0.0); (2.0; 3.0); (4.5; 2.0); (10.0; 1.1)";
        assertEquals(expectedString, linkedList.toString());
    }

    @Test
    public void testClone1() {
        LinkedListTabulatedFunction original = new LinkedListTabulatedFunction();
        LinkedListTabulatedFunction cloned = original.Clone();

        assertNotNull(cloned);
        assertNotSame(original, cloned);
        assertArrayEquals(original.getNode(), cloned.getNode());
    }

    @Test
    public void testClone2() {
        double[] xArr1 = {1.0, 2.0, 4.5, 10.0};
        double[] yArr1 = {0.0, 3.0, 2.0, 1.1};
        LinkedListTabulatedFunction original = new LinkedListTabulatedFunction(xArr1, yArr1);
        LinkedListTabulatedFunction cloned = original.Clone();

        assertNotNull(cloned);
        assertNotSame(original, cloned);
        assertArrayEquals(original.getNode(), cloned.getNode());
    }

    @Test
    public void testCloneNodes() {
        LinkedListTabulatedFunction originalFunction = new LinkedListTabulatedFunction();
        originalFunction.add(1.0, 2.0);
        originalFunction.add(2.0, 4.0);
        originalFunction.add(3.0, 6.0);
        LinkedListTabulatedFunction cloned = originalFunction.Clone();

        assertNotNull(cloned);
        assertNotSame(originalFunction, cloned);
        assertArrayEquals(originalFunction.getNode(), cloned.getNode());

    }
    @Test

    void testConstructorDifferentLengthOfArraysException() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0};

        assertThrows(DifferentLengthOfArraysException.class,
                () -> new LinkedListTabulatedFunction(xValues, yValues));
    }
}