package com.alexsid.lesson10.tasks13;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SorterInvocationHandler implements InvocationHandler {
    private final Sorter sorter;

    public SorterInvocationHandler(Sorter sorter) {
        this.sorter = sorter;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke;
        Long start = System.nanoTime();
        invoke = method.invoke(sorter, args);
        Long finish = System.nanoTime();
        int length = ((int[]) args[0]).length;
        try(PrintStream out = new PrintStream(new FileOutputStream("src/main/java/com/alexsid/lesson10/tasks13/output.txt", true))) {
            out.println(method.getName() + " take " + (finish - start) + " nanoseconds for array of " + length + " elements");
        }
        return invoke;
    }
}
