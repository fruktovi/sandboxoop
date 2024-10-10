package ru.ssau.tk.fruktovi.sandboxoop.functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {

    private double[] xValues;
    private double[] yValues;
    private int count;


    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {

        this.count = xValues.length;
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
}

