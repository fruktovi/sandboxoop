package ru.ssau.tk.fruktovi.sandboxoop.operations;



import ru.ssau.tk.fruktovi.sandboxoop.functions.TabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.Point;

public class TabulatedFunctionOperationService{
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
