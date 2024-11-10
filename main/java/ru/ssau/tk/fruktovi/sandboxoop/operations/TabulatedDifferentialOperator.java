package ru.ssau.tk.fruktovi.sandboxoop.operations;

import ru.ssau.tk.fruktovi.sandboxoop.functions.Point;
import ru.ssau.tk.fruktovi.sandboxoop.functions.TabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.fruktovi.sandboxoop.functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction>{

    private TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        double[] xValues = new double[function.getCount()];
        double[] yValues = new double[function.getCount()];

        for (int k = 0; k < points.length-1; k++) {
            xValues[k] = function.getX(k);
            yValues[k] = (function.getY(k+1) - function.getY(k))/(function.getX(k+1)-function.getX(k));
        }
        xValues[points.length-1] = function.getX(points.length-1);
        yValues[points.length-1] = (function.getY(points.length-1) - function.getY(points.length-2))/(function.getX(points.length-1)-function.getX(points.length-2));

        return factory.create(xValues, yValues);
    }

    @Override
    public double apply(double x) {
        return 0;
    }
}