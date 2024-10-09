package ru.ssau.tk.fruktovi.sandboxoop.functions;

public class RK_Algorithm implements MathFunction {
    @Override
    public double apply(double x) {
        // Например, определим dy/dx = f(x, y) = y (простое уравнение y')
        double h = 0.1; // шаг
        double y = 1; // начальное значение
        double k1 = h * y;
        double k2 = h * (y + 0.5 * k1);
        double k3 = h * (y + 0.5 * k2);
        double k4 = h * (y + k3);
        return y + (k1 + 2 * k2 + 2 * k3 + k4) / 6; // новое значение y
    }
}




