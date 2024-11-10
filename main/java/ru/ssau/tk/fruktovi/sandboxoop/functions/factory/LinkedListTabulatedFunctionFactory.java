package ru.ssau.tk.fruktovi.sandboxoop.functions.factory;

import ru.ssau.tk.fruktovi.sandboxoop.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.TabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory{
    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}