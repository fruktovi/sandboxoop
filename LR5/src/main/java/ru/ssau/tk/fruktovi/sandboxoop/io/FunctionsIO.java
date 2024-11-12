package ru.ssau.tk.fruktovi.sandboxoop.io;
import ru.ssau.tk.fruktovi.sandboxoop.functions.Point;
import ru.ssau.tk.fruktovi.sandboxoop.functions.TabulatedFunction;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;


public final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException("Instances of FunctionsIO are not allowed.");
    }
    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function){
        PrintWriter file = new PrintWriter(writer);

        int count = function.getCount();

        file.println(count);

        for(Point point : function){
            double x = point.x;
            double y = point.y;
            file.printf("%f %f\n",x,y);
        }
        file.flush();
    }
}
