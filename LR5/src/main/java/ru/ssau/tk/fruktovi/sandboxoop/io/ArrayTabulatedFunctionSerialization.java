package ru.ssau.tk.fruktovi.sandboxoop.io;


import ru.ssau.tk.fruktovi.sandboxoop.functions.ArrayTabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.TabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.operations.TabulatedDifferentialOperator;

import java.io.*;
import java.nio.file.Paths;

public class ArrayTabulatedFunctionSerialization {

    public static void main(String[] args) {
        String filename = "C:\\Users\\user\\IdeaProjects\\sandboxoop\\LR5\\output\\serialized_array_functions.bin";

        TabulatedFunction arrayTabulatedFunction =
                new ArrayTabulatedFunction(new double[]{1.7, 2.8, 6.5}, new double[]{5.0, 8.3, 9.19});

        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator();
        TabulatedFunction firstDerivative = differentialOperator.derive(arrayTabulatedFunction);
        TabulatedFunction secondDerivative = differentialOperator.derive(firstDerivative);

        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filename))) {
            FunctionsIO.serialize(bufferedOutputStream, arrayTabulatedFunction);
            FunctionsIO.serialize(bufferedOutputStream, firstDerivative);
            FunctionsIO.serialize(bufferedOutputStream, secondDerivative);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filename))) {
            TabulatedFunction deserializedArrayTabulatedFunction = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction deserializedFirstDerivative = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction deserializedSecondDerivative = FunctionsIO.deserialize(bufferedInputStream);

            System.out.println("Исходная функция: " + deserializedArrayTabulatedFunction.toString());
            System.out.println("Первая производная исходной функции: " + deserializedFirstDerivative.toString());
            System.out.println("Вторая производная исходной функции: " + deserializedSecondDerivative.toString());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}

