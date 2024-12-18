package ru.ssau.tk.fruktovi.sandboxoop.ui.SpecialFunctions;

import ru.ssau.tk.fruktovi.sandboxoop.functions.MathFunction;

public class SinusFunction implements MathFunction {
    private final double constant;
    public SinusFunction(){
        this.constant = 1;
    }
    @Override
    public double apply(double x) {
        return constant*Math.sin(x);
    }
}
