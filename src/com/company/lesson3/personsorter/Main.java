
package com.company.lesson3.personsorter;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размер массива");
        int numberOfPeople = scanner.nextInt();
        Person[] persons = PersonListInitializer.getInstance(numberOfPeople);
        long start = System.currentTimeMillis();
        PersonSorter sorter = new FastPersonSorter();
//        PersonSorter sorter = new BubblePersonSorter();
        sorter.sort(persons);
        long finish = System.currentTimeMillis();
        System.out.println(Arrays.toString(persons));
        System.out.printf("%nВремя работы алгоритма составило: %d миллисекунд(ы)%n", finish - start);

    }
}