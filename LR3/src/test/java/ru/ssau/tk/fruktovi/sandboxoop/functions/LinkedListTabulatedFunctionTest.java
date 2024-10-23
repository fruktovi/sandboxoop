package ru.ssau.tk.fruktovi.sandboxoop.functions;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionTest {

    @Test
    void floorIndexOfX() {
        double[] x = {1, 1.5, 2.5, 10, 11};
        double[] y = {2, 2, 3, 4, -5};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(x,y);
        assertEquals(2, test.floorIndexOfX(3.1));
    }

    @Test
    void extrapolateLeft() {
        double[] x = {1, 2, 2.5, 10, 11};
        double[] y = {2, 4, 3, 4,-5};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(x,y);
        assertEquals(-2, test.extrapolateLeft(-1));
    }

    @Test
    void extrapolateRight() {
        double[] x = {0, 1, 2, 3, 4, 5};
        double[] y = {0, 2, 1, 2, 16, 20};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(x,y);
        assertEquals(24, test.extrapolateRight(6));
    }

    @Test
    void interpolate() {
        double[] x = {1, 2, 5, 10 ,11};
        double[] y = {2, 4, 3, 4, -4};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(x,y);
        assertEquals(44, test.interpolate(5,test.floorIndexOfX(5)));
    }

    @Test
    void getCount() {
        MathFunction func = new SqrFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,10,10);
        assertEquals(10, test.getCount());
    }

    @Test
    void getX() {
        MathFunction func = new SqrFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,10);
        assertEquals(0.0, test.getX(9), 0.001);
    }

    @Test
    void getY() {
        MathFunction func = new SqrFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,9);
        assertEquals(0.0, test.getY(8));
    }

    @Test
    void setY() {
        MathFunction func = new SqrFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,9);
        test.setY(2,10);
        assertEquals(10, test.getY(2));
    }

    @Test
    void indexOfX() {
        double[] x = {1, 1.5, 2.5, 10, 11};
        double[] y = {2, 2, 3, 4,-5};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(x,y);
        assertEquals(1, test.indexOfX(1.5));
    }

    @Test
    void indexOfY() {
        MathFunction func = new IdentityFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,6);
        assertEquals(1, test.indexOfY(1));
    }

    @Test
    void leftBound() {
        MathFunction func = new IdentityFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,5);
        assertEquals(0, test.leftBound());
    }

    @Test
    void rightBound() {
        MathFunction func = new IdentityFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,5);
        assertEquals(5, test.rightBound());
    }
    @Test
    void remove1() {
        double[] x = {1, 2, 5, 10 ,11};
        double[] y = {2, 4, 3, 4, -4};
        int index = 2;
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(x, y);
        double removed_obj = test.getX(index+1);
        test.remove(index);
        assertEquals(removed_obj, test.getX(index));
    }

    @Test
    void remove2() {
        double[] x = {1, 2, 5, 10 ,11};
        double[] y = {2, 4, 3, 4, -4};
        int index = 0;
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(x, y);
        double obj = test.getX(index+1);
        test.remove(index);
        assertEquals(obj, test.getX(index));
    }
    @Test
    void insert1() {
        MathFunction func = new IdentityFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,6);
        test.insert(2.5, 22222);
        assertEquals(3.0, test.getY(3));
    }
    @Test
    void insert2() {
        MathFunction func = new IdentityFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,6);
        test.insert(-1, 22222);
        assertEquals(-1, test.getX(0));
    }
    @Test
    void insert3() {
        MathFunction func = new IdentityFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,5,6);
        test.insert(6, 22222);
        assertEquals(22222, test.getY(6));
    }
    @Test
    public void testToString() {
        double[] xArray = {9.0, 6.7, 4.5, 10.0};
        double[] yArray = {8.0, 7.0, 2.0, 1.8};
        LinkedListTabulatedFunction linkedList = new LinkedListTabulatedFunction(xArray, yArray);

        String expectedString = "(9.0; 8.0); (6.7; 7.0); (4.5; 2.0); (10.0; 1.8)";
        assertEquals(expectedString, linkedList.toString());
    }
    @Test
    public void testEquals() {
        double[] xArray1 = {1.0, 2.0, 4.5, 10.0};
        double[] yArray1 = {0.0, 3.0, 2.0, 1.1};
        LinkedListTabulatedFunction linkedList1 = new LinkedListTabulatedFunction(xArray1, yArray1);

        double[] xArray2 = {1.0, 2.0, 4.5, 10.0};
        double[] yArray2 = {0.0, 3.0, 2.0, 1.1};
        LinkedListTabulatedFunction linkedList2 = new LinkedListTabulatedFunction(xArray2, yArray2);

        Assertions.assertTrue(linkedList1.equals(linkedList2));
    }
    @Test
    public void testNotEquals() {
        double[] xArray1 = {1.0, 2.0, 4.5, 10.0};
        double[] yArray1 = {0.0, 3.0, 2.0, 1.1};
        LinkedListTabulatedFunction linkedList1 = new LinkedListTabulatedFunction(xArray1, yArray1);

        double[] xArray2 = {1.0, 3.0, 4.5, 10.0};
        double[] yArray2 = {0.0, 2.0, 2.0, 1.1};
        LinkedListTabulatedFunction linkedList2 = new LinkedListTabulatedFunction(xArray2, yArray2);

        Assertions.assertFalse(linkedList1.equals(linkedList2));
    }
    @Test
    public void testHashCode() {
        double[] xArray = {1.0, 2.0, 4.5, 10.0};
        double[] yArray = {0.0, 3.0, 2.0, 1.1};
        LinkedListTabulatedFunction linkedList1 = new LinkedListTabulatedFunction(xArray, yArray);
        LinkedListTabulatedFunction linkedList2 = new LinkedListTabulatedFunction(xArray, yArray);

        // Если объекты равны (equals), то их хэш-коды тоже должны быть равны
        assertEquals(linkedList1.hashCode(), linkedList2.hashCode());
    }

}