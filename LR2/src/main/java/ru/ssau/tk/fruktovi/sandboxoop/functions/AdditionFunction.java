package ru.ssau.tk.fruktovi.sandboxoop.functions;

public class AdditionFunction implements MathFunction {
    private double addend;

    public AdditionFunction(double addend) {
        this.addend = addend;
    }

    @Override
    public double apply(double x) {
        return x + addend;
    }
}



