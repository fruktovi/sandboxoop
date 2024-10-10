package ru.ssau.tk.fruktovi.sandboxoop.functions;

public class RK_Algorithm implements MathFunction {
    private final double x0;
    private final double y0;
    private final double h;


    public RK_Algorithm(double x0, double y0, double h) {
        this.x0 = x0;
        this.y0 = y0;
        this.h = h;
    }


    public static double f(double x, double y) {
        return x + y;
    }

    // Runge-Kutta 4th order method
    public double rungeKutta(double x, double y) {
        double k1 = h * f(x, y);
        double k2 = h * f(x + 0.5 * h, y + 0.5 * k1);
        double k3 = h * f(x + 0.5 * h, y + 0.5 * k2);
        double k4 = h * f(x + h, y + k3);
        return y + (1.0 / 6.0) * (k1 + 2 * k2 + 2 * k3 + k4);
    }


    @Override
    public double apply(double x) {
        double currentX = x0;
        double currentY = y0;

        while (currentX < x) {
            currentY = rungeKutta(currentX, currentY);
            currentX += h;
        }

        return currentY;
    }
}
