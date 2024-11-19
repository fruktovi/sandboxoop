package ru.ssau.tk.fruktovi.sandboxoop.concurrent;

import ru.ssau.tk.fruktovi.sandboxoop.functions.TabulatedFunction;

public class MultiplyingTask implements Runnable{


    private final TabulatedFunction function;

    public MultiplyingTask(TabulatedFunction function) {
        this.function = function;
    }

    @Override
    public void run() {
        for (int i = 0; i < function.getCount(); ++i) {
            synchronized (function) {
                function.setY(i, function.getY(i) * 2);
            }
        }

        System.out.printf("Поток %s закончил выполнение задачи\n", Thread.currentThread().getName());
    }


}
