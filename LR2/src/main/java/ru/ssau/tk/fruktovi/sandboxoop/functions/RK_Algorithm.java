package ru.ssau.tk.fruktovi.sandboxoop.functions;

import java.util.function.BiFunction;

public class RK_Algorithm implements MathFunction {

    private final BiFunction<Double, Double, Double> differentialEquation; //  дифференциальное уравнение
    private final double x0;
    private final double y0;


    public RK_Algorithm(BiFunction<Double, Double, Double> differentialEquation, double x0, double y0) {
        this.differentialEquation = differentialEquation;
        this.x0 = x0;
        this.y0 = y0;
    }

    @Override
    public double apply(double step) {
        double k1, k2, k3, k4;

        k1 = this.differentialEquation.apply(this.x0, this.y0);
        k2 = this.differentialEquation.apply(this.x0+step/2, this.y0+step*k1/2);
        k3 = this.differentialEquation.apply(this.x0+step/2, this.y0+step*k2/2);
        k4 = this.differentialEquation.apply(this.x0+step, this.y0+step*k3);

        return (this.y0 + (step/6) * (k1+2*k2+2*k3+k4));
    }
}