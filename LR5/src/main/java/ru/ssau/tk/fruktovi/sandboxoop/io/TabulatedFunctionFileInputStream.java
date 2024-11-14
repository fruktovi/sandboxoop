package ru.ssau.tk.fruktovi.sandboxoop.io;

import ru.ssau.tk.fruktovi.sandboxoop.functions.TabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.fruktovi.sandboxoop.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.fruktovi.sandboxoop.operations.TabulatedDifferentialOperator;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try(BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("D:\\java dev\\sandboxoop\\LR5\\input\\binary function.bin"))){
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(inputStream, new ArrayTabulatedFunctionFactory());
            System.out.println(function.toString());
        }catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Enter the size and values of the function: ");

        try{
            TabulatedFunction consoleFunction = FunctionsIO.readTabulatedFunction(new BufferedReader(new InputStreamReader(System.in)), new LinkedListTabulatedFunctionFactory());

            System.out.println(new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory()).derive(consoleFunction).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}