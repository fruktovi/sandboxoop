package ru.ssau.tk.fruktovi.sandboxoop.functions;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import ru.ssau.tk.fruktovi.sandboxoop.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.fruktovi.sandboxoop.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.fruktovi.sandboxoop.exceptions.InterpolationException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable {

    private double[] xValues;
    private double[] yValues;
    private int count;


    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {

        this.count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
        // Проверка на одинаковую длину массивов
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("Arrays have different lengths.");
        }
        for (int i = 1; i < xValues.length; i++) {
            if (xValues[i] <= xValues[i - 1]) {
                throw new ArrayIsNotSortedException("Array is not sorted.");
            }
        }
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);





    }


    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {


        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }

        this.count = count;
        this.xValues = new double[count];
        this.yValues = new double[count];

        double step = (xTo - xFrom) / (count - 1);

        for (int i = 0; i < count; i++) {
            xValues[i] = xFrom + i * step;
            yValues[i] = source.apply(xValues[i]);
        }
    }

    @Override
    protected int floorIndexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] > x) {
                return i - 1;
            }
        }
        return count - 1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return yValues[0];
    }

    @Override
    protected double extrapolateRight(double x) {
        return yValues[count - 1];
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (x < xValues[floorIndex] || x > xValues[floorIndex + 1]) {
            throw new InterpolationException("x is out of interpolation range.");
        }

        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);

    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {

        return xValues[index];
    }

    @Override
    public double getY(int index) {

        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {

        yValues[index] = value;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public void insert(double x, double y) {
        int i = 0;
        while(getX(i) < x && i < count) ++i;
        if(getX(i) == x) setY(i, y);
        else if (i < count){
            double[] xTempFull = new double[count+1];
            double[] yTempFull = new double[count+1];
            System.arraycopy(xValues, 0, xTempFull, 0, i);
            xTempFull[i] = x;
            System.arraycopy(xValues, i, xTempFull, i + 1, count - i);
            xValues = new double[count+1];
            System.arraycopy(xTempFull, 0, xValues, 0, count + 1);

            System.arraycopy(yValues, 0, yTempFull, 0, i);
            yTempFull[i] = y;
            System.arraycopy(yValues, i, yTempFull, i + 1, count - i);
            yValues = new double[count+1];
            System.arraycopy(yTempFull, 0, yValues, 0, count + 1);
            ++count;
        }
        else{
            double[] xTempFull = new double[count+1];
            double[] yTempFull = new double[count+1];
            System.arraycopy(xValues, 0, xTempFull, 0, i);
            xTempFull[i] = x;
            xValues = new double[count+1];
            System.arraycopy(xTempFull, 0, xValues, 0, count + 1);

            System.arraycopy(yValues, 0, yTempFull, 0, i);
            yTempFull[i] = y;
            yValues = new double[count+1];
            System.arraycopy(yTempFull, 0, yValues, 0, count + 1);
            ++count;
        }
    }

    @Override
    public void remove(int index) {
        if(Double.isNaN(getX(index))){
            System.out.println("Index doesn't exist");
            return;
        }
        else if (index == count - 1) { --count; return; }
        double[] xTempFull = new double[count-1];
        double[] yTempFull = new double[count-1];
        System.arraycopy(xValues, 0, xTempFull, 0, index);
        System.arraycopy(xValues, index + 1, xTempFull, index, count - index - 1);
        System.arraycopy(xTempFull, 0, xValues, 0, count - 1);

        System.arraycopy(yValues, 0, yTempFull, 0, index);
        System.arraycopy(yValues, index + 1, yTempFull, index, count - index - 1);
        System.arraycopy(yTempFull, 0, yValues, 0, count - 1);
        --count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Сравниваем ссылки
        if (!(o instanceof ArrayTabulatedFunction)) return false; // Проверка типа
        ArrayTabulatedFunction other = (ArrayTabulatedFunction) o; // Приведение типа

        return Arrays.equals(this.xValues, other.xValues) && Arrays.equals(this.yValues, other.yValues); // Сравнение массивов
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ArrayTabulatedFunction:\n");
        for (int i = 0; i < count; i++) {
            builder.append("x: ").append(xValues[i])
                    .append(", y: ").append(yValues[i]).append("\n");
        }
        return builder.toString();
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(xValues);
        result = 31 * result + Arrays.hashCode(yValues);
        return result;
    }


    @Override
    public ArrayTabulatedFunction clone() {

        ArrayTabulatedFunction clone = new ArrayTabulatedFunction(
                Arrays.copyOf(this.xValues, this.xValues.length),
                Arrays.copyOf(this.yValues, this.yValues.length)
        );
        return clone;
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(xValues[i], yValues[i]);
                i++;
                return point;
            }
        };
    }
}
