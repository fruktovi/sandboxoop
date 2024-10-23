package ru.ssau.tk.fruktovi.sandboxoop.functions;

public class IdentityFunction implements MathFunction {

    @Override
    public double apply(double x) {
        return x;
    }

    @Override
    public String toString() {
        return "IdentityFunction: f(x) = x";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof IdentityFunction;
    }

    @Override
    public int hashCode() {
        int hash = 31;  // Можно использовать любое фиксированное число, так как все экземпляры считаются равными
        return hash;
    }

    @Override
    public IdentityFunction clone() {
        try {
            return (IdentityFunction) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Не должно произойти
        }
    }
}