package ru.ssau.tk.fruktovi.sandboxoop.functions;


import ru.ssau.tk.fruktovi.sandboxoop.exceptions.DifferentLengthOfArraysException;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    protected int count;
    protected abstract int floorIndexOfX(double x);
    protected abstract double extrapolateLeft(double x);
    protected abstract double extrapolateRight(double x);
    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + ((rightY - leftY) / (rightX - leftX)) * (x - leftX);
    }

    @Override
    public int getCount() {
            return count;
        }


    @Override
    public double apply(double x) {

        if (x < getX(0)) {
            return extrapolateLeft(x);
        }

        else if (x > getX(getCount() - 1)) {
            return extrapolateRight(x);
        } else {
            int index = indexOfX(x);
            if (index != -1) {
                return getY(index);
            } else {
                index = floorIndexOfX(x);
                return interpolate(x, index);
            }

        }
    }
    // Проверка на одинаковую длину массивов
    public static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("Arrays have different lengths.");
        }
    }



    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getClass().getSimpleName() + " size = " + this.count + "\n");
        for (Point point : this) {
            stringBuilder.append("[").append(point.x).append("; ").append(point.y).append("]\n");
        }
        return stringBuilder.toString();
    }

}

