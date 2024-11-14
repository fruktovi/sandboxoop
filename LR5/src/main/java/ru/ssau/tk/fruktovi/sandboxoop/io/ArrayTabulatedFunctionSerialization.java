package ru.ssau.tk.fruktovi.sandboxoop.io;


import ru.ssau.tk.fruktovi.sandboxoop.functions.ArrayTabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.operations.TabulatedDifferentialOperator;

import java.io.*;
public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {

        String filename = "C:/Users/user/IdeaProjects/sandboxoop/LR5/output/serialized_array_functions.bin";


        double[] xValues = {0, 1, 2, 3, 4, 5};
        double[] yValues = {0, 1, 4, 9, 16, 25};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);


        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        ArrayTabulatedFunction firstDerivative = (ArrayTabulatedFunction) operator.derive(function);
        ArrayTabulatedFunction secondDerivative = (ArrayTabulatedFunction) operator.derive(firstDerivative);


        try (FileOutputStream fileOut = new FileOutputStream(filename);
             BufferedOutputStream bufferedOut = new BufferedOutputStream(fileOut)) {

            FunctionsIO.serialize(bufferedOut, function);
            FunctionsIO.serialize(bufferedOut, firstDerivative);
            FunctionsIO.serialize(bufferedOut, secondDerivative);

        } catch (IOException e) {
            e.printStackTrace();
        }


        try (FileInputStream fileIn = new FileInputStream(filename);
             BufferedInputStream bufferedIn = new BufferedInputStream(fileIn)) {

            ArrayTabulatedFunction deserializedFunction = (ArrayTabulatedFunction) FunctionsIO.deserialize(bufferedIn);
            ArrayTabulatedFunction deserializedFirstDerivative = (ArrayTabulatedFunction) FunctionsIO.deserialize(bufferedIn);
            ArrayTabulatedFunction deserializedSecondDerivative = (ArrayTabulatedFunction) FunctionsIO.deserialize(bufferedIn);

            // Вывод значений
            System.out.println("Original Function: \n" + deserializedFunction.toString());
            System.out.println("First Derivative: \n" + deserializedFirstDerivative.toString());
            System.out.println("Second Derivative: \n" + deserializedSecondDerivative.toString());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

