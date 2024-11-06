package ru.ssau.tk.fruktovi.sandboxoop.functions.factory;


import ru.ssau.tk.fruktovi.sandboxoop.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}
