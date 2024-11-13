package ru.ssau.tk.fruktovi.sandboxoop.io;

import ru.ssau.tk.fruktovi.sandboxoop.functions.ArrayTabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.LinkedListTabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {

        ArrayTabulatedFunction arrayFunction = new ArrayTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{4.0, 5.0, 6.0});
        LinkedListTabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{4.5, 6.7, 8.9});

        try (BufferedOutputStream bufferedOutputStream1 = new BufferedOutputStream(new FileOutputStream("D:\\java dev\\sandboxoop\\LR5\\output/array function.bin"));
             BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream("D:\\java dev\\sandboxoop\\LR5\\output/linked list function.bin"))) {

            FunctionsIO.writeTabulatedFunction(bufferedOutputStream1, arrayFunction);
            FunctionsIO.writeTabulatedFunction(bufferedOutputStream2, linkedListFunction);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}