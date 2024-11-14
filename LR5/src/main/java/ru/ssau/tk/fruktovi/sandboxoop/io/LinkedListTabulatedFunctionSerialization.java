package ru.ssau.tk.fruktovi.sandboxoop.io;

import ru.ssau.tk.fruktovi.sandboxoop.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.TabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.fruktovi.sandboxoop.operations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {

        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("D:\\java dev\\sandboxoop\\LR5\\output\\serialized linked list functions.bin"))){
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());

            TabulatedFunction tabulatedFunction1 = new LinkedListTabulatedFunction(x -> Math.pow(x,2) +x+1, -3, 3, 5);
            TabulatedFunction derivedFunction1 = operator.derive(tabulatedFunction1);
            TabulatedFunction derivedFunction2 = operator.derive(derivedFunction1);

            FunctionsIO.serialize(bufferedOutputStream, tabulatedFunction1);
            FunctionsIO.serialize(bufferedOutputStream, derivedFunction1);
            FunctionsIO.serialize(bufferedOutputStream, derivedFunction2);
            bufferedOutputStream.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("D:\\java dev\\sandboxoop\\LR5\\output\\serialized linked list functions.bin"))){
            System.out.println(FunctionsIO.deserialize(bufferedInputStream).toString());
            System.out.println(FunctionsIO.deserialize(bufferedInputStream).toString());
            System.out.println(FunctionsIO.deserialize(bufferedInputStream).toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}