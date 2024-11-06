package ru.ssau.tk.fruktovi.sandboxoop.operations;



import ru.ssau.tk.fruktovi.sandboxoop.functions.TabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.Point;
import ru.ssau.tk.fruktovi.sandboxoop.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.fruktovi.sandboxoop.functions.factory.ArrayTabulatedFunctionFactory;

public class TabulatedFunctionOperationService{
    private final TabulatedFunctionFactory factory;

    // Конструктор с параметром
    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    // Конструктор без параметров, используется ArrayTabulatedFunctionFactory по умолчанию
    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }



    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int size = tabulatedFunction.getCount();
        Point[] points = new Point[tabulatedFunction.getCount()];
        int i = 0;

        for (Point point : tabulatedFunction) {
            points[i] = point;
            i++;
        }

        return points;
    }
}
