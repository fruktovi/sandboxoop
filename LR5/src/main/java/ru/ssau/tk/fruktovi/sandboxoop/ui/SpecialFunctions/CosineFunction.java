package ru.ssau.tk.fruktovi.sandboxoop.ui.SpecialFunctions;


import ru.ssau.tk.fruktovi.sandboxoop.functions.MathFunction;

import java.lang.Math;

public class CosineFunction implements MathFunction {
    private final double constant;
    public CosineFunction(){
        this.constant = 1;
    }
    @Override
    public double apply(double x) {
        return constant*Math.cos(x);
    }
}
