package com.alexsid.lesson3.helloworld;

import java.util.Random;

/**1. Написать программу ”Hello, World!”.

 В ходе выполнения программы она должна выбросить исключение и завершиться с ошибкой:

 Смоделировав ошибку «NullPointerException»
 Смоделировав ошибку «ArrayIndexOutOfBoundsException»
 Вызвав свой вариант ошибки через оператор throw
 */
public class HelloWorld {
    public static void main(String[] args) {
        int number = new Random().nextInt(15);
        String hello;
        if (number > 5){
            hello = "hello_world";
        }else {
            hello = null;
        }
        char letter = hello.charAt(number);
        //if you still here
        throw new MyException();
    }

    private static class MyException extends RuntimeException {
    }
}
