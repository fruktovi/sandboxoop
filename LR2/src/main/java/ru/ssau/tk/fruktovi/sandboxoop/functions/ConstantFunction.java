package ru.ssau.tk.fruktovi.sandboxoop.functions;

public class ConstantFunction {
    private final double constant;

    public ConstantFunction(double constant) {
        this.constant = constant;
    }

    public double apply(double x) {
        return constant;
    }

    public double getConstant() {
        return constant;
    }
}
