package ru.ssau.tk.fruktovi.sandboxoop.io;
import ru.ssau.tk.fruktovi.sandboxoop.functions.Point;
import ru.ssau.tk.fruktovi.sandboxoop.functions.TabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.factory.TabulatedFunctionFactory;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;


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
    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        try {
            int count = Integer.parseInt(reader.readLine());

            double[] xValues = new double[count];
            double[] yValues = new double[count];

            NumberFormat numberFormatter = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
            for (int i = 0; i < count; i++) {
                String line = reader.readLine();
                String[] parts = line.split(" ");

                try {
                    xValues[i] = numberFormatter.parse(parts[0]).doubleValue();
                    yValues[i] = numberFormatter.parse(parts[1]).doubleValue();
                } catch (ParseException e) {
                    throw new IOException();
                }
            }
            return factory.create(xValues, yValues);
        } catch (IOException ex) {
            throw ex;
        } catch (NumberFormatException e) {
            throw new IOException();
        }
    }
    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(function.getCount());
        for (Point point : function){
            dataOutputStream.writeDouble(point.x);
            dataOutputStream.writeDouble(point.y);
        }
        dataOutputStream.flush();
    }
    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {
        ObjectOutputStream objOutputStream = new ObjectOutputStream(stream);
        objOutputStream.writeObject(function);
        objOutputStream.flush();
    }
}
