package ru.ssau.tk.fruktovi.sandboxoop.functions;

public class MultiplicationFunction implements MathFunction {
    private double factor;

    public MultiplicationFunction(double factor) {
        this.factor = factor;
    }

    @Override
    public double apply(double x) {
        return x * factor;
    }
}
