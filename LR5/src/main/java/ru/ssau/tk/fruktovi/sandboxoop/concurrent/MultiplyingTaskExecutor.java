package ru.ssau.tk.fruktovi.sandboxoop.concurrent;

import ru.ssau.tk.fruktovi.sandboxoop.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.TabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.UnitFunction;

import java.util.ArrayList;

import java.util.List;

public class MultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction linkedListFunction =
                new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 1000);


        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            MultiplyingTask multiplyingTask = new MultiplyingTask(linkedListFunction);
            threads.add(new Thread(multiplyingTask));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        Thread.sleep(2000);

        System.out.println("Табулированная функция после умножения:");
        for (int i = 0; i < linkedListFunction.getCount(); i++) {
            System.out.printf("x: %f, y: %f%n", linkedListFunction.getX(i), linkedListFunction.getY(i));
        }
    }
}
