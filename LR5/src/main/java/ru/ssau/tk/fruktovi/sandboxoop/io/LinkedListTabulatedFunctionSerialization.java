package ru.ssau.tk.fruktovi.sandboxoop.io;

import ru.ssau.tk.fruktovi.sandboxoop.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.TabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.fruktovi.sandboxoop.operations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {

    public static void main(String[] args) {

        String filename = "D:\\java dev\\sandboxoop\\LR5\\output\\serialized_linked_list_functions.bin";


        double[] xValues = {0, 1, 2, 3, 4, 5};
        double[] yValues = {0, 1, 4, 9, 16, 25};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        LinkedListTabulatedFunction firstDerivative = (LinkedListTabulatedFunction) operator.derive(function);
        LinkedListTabulatedFunction secondDerivative = (LinkedListTabulatedFunction) operator.derive(firstDerivative);

        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            FunctionsIO.serialize(bufferedOutputStream, function);
            FunctionsIO.serialize(bufferedOutputStream, firstDerivative);
            FunctionsIO.serialize(bufferedOutputStream, secondDerivative);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fileInputStream = new FileInputStream(filename);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            LinkedListTabulatedFunction deserializedFunction = (LinkedListTabulatedFunction) FunctionsIO.deserialize(bufferedInputStream);
            LinkedListTabulatedFunction deserializedFirstDerivative = (LinkedListTabulatedFunction) FunctionsIO.deserialize(bufferedInputStream);
            LinkedListTabulatedFunction deserializedSecondDerivative = (LinkedListTabulatedFunction) FunctionsIO.deserialize(bufferedInputStream);

            System.out.println("Original Function: " + deserializedFunction);
            System.out.println("First Derivative: " + deserializedFirstDerivative);
            System.out.println("Second Derivative: " + deserializedSecondDerivative);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}