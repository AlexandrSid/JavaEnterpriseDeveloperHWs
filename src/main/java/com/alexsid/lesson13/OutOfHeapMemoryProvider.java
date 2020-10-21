package com.alexsid.lesson13;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OutOfHeapMemoryProvider {//run with VM -verbose:gc option
    public static void main(String[] args) {
        List<String> stringStorage = new ArrayList<>();

        for (int i = 0; i < 100000000; i++) {
            String generated = generateString(300000000);
//            Exception in thread "main" java.lang.OutOfMemoryError: Java heap space при размере строки в 30 000 000 символов
            stringStorage.add(generated);
        }
    }

    private static String generateString(int size) {
        Random random = new Random();//это будет удаляемый мусор
        StringBuilder builder = new StringBuilder();//это тоже
        for (int i = 0; i < size; i++) {
            builder.append((char)random.nextInt(256));
        }
        return builder.toString();
    }
}
