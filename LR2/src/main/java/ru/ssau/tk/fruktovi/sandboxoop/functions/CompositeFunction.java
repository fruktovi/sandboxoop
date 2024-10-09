package ru.ssau.tk.fruktovi.sandboxoop.functions;

import java.awt.*;

public class CompositeFunction implements MathFunction {
    private MathFunction func1;
    private MathFunction func2;

    public CompositeFunction(MathFunction f1, MathFunction f2){
        func1=f1;
        func2=f2;
    }

    @Override
    public double apply(double x) {
        return func2.apply(func1.apply(x));
    }
}
