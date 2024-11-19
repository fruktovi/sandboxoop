package ru.ssau.tk.fruktovi.sandboxoop.concurrent;

import ru.ssau.tk.fruktovi.sandboxoop.functions.ConstantFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.TabulatedFunction;

public class ReadWriteTaskExecutor {

    public static void main(String[] args) {
        TabulatedFunction linkedListTabulatedFunction =
                new LinkedListTabulatedFunction(new ConstantFunction(-1), (double) 1.0, 1000.0, 1000);

        Thread thread1 = new Thread(new ReadTask(linkedListTabulatedFunction));
        Thread thread2 = new Thread(new WriteTask(linkedListTabulatedFunction, 0.5));

        thread1.start();
        thread2.start();
    }

}