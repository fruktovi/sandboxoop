package ru.ssau.tk.fruktovi.sandboxoop.operations;

import ru.ssau.tk.fruktovi.sandboxoop.functions.MathFunction;

public interface DifferentialOperator<T> extends MathFunction {
    T derive(T function);
}