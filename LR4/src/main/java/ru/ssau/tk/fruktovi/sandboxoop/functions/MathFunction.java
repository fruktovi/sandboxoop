package ru.ssau.tk.fruktovi.sandboxoop.functions;

interface MathFunction {
    double apply(double x);
    default MathFunction andThen(MathFunction afterFunction) {
        return (double x) -> afterFunction.apply(this.apply(x));
    }
}