package main.java.com.alexsid.lesson10;

import java.lang.reflect.Proxy;
import java.util.Random;

/**
 * 1. Необходимо создать класс, который выполняет 2 вида сортировки массива (коллекции):
 * - Сортировка “пузырьком”;
 * - Сортировка с помощью стандартной java реализации - Arrays.sort() (или Collections.sort())
 * <p>
 * 2. Создать прокси класс, который замеряет время работы методов базового класса и выводит результат в некий файл
 * <p>
 * 3. Замерить разницу в скорости сортировки для 10000, 100000 и 1000000 элементов для каждого из методов.
 * Для бОльшей достоверности, запустить измерения по 10 раз на каждый метод для каждого из заданных объемов
 */

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        Sorter ordinarySorter = new SorterImpl();

        ClassLoader sorterClassLoader = ordinarySorter.getClass().getClassLoader();
        Class<?>[] sorterInterfaces = ordinarySorter.getClass().getInterfaces();

        Sorter proxySorter = (Sorter) Proxy.newProxyInstance(sorterClassLoader, sorterInterfaces, new SorterInvocationHandler(ordinarySorter));

        int[] sizes = {10000, 100000, 1000000};
        for (int size: sizes) {
            for (int i = 0; i < 10; i++) {
                int[] arr = generateArray(size, 0, 100000);
                proxySorter.standardSort(arr);
            }
            for (int i = 0; i < 10; i++) {
                int[] arr = generateArray(size, 0, 100000);
                if (size > 100000) break;//иначе по 30+ минут на одну сортировку для миллиона элементов
                proxySorter.bubbleSort(arr);
            }
        }

    }

    private static int[] generateArray(int size, int min, int max) {
        int[] result = new int[size];
        Random rd = new Random();
        for (int i = 0; i < size; i++) {
            result[i] = rd.nextInt(max - min) + min;
        }
        return result;
    }
}
