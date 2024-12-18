package ru.ssau.tk.fruktovi.sandboxoop.ui.SpecialFunctions;

import ru.ssau.tk.fruktovi.sandboxoop.functions.MathFunction;

import java.lang.Math;

public class TangentFunction implements MathFunction {
    private final double constant;
    public TangentFunction(){
        this.constant = 1;
    }
    @Override
    public double apply(double x) {
        return constant*Math.tan(x);
    }
}
