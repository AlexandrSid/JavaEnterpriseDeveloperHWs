package com.alexsid.lesson3.personsorter;

import java.util.Comparator;
import java.util.Random;

public class FastPersonSorter implements PersonSorter {
    @Override
    public void sort(Person[] persons) {
        quicksort(persons, 0, persons.length - 1);

    }

    Random pivotRng = new Random();


    private <T> void quicksort(T[] items, int left, int right) {
        if (left < right) {
            int pivotIndex = pivotRng.nextInt(right - left) + left;
            int newPivot = partition(items, left, right, pivotIndex);

            quicksort(items, left, newPivot - 1);
            quicksort(items, newPivot + 1, right);
        }
    }

    private <T> int partition(T[] items, int left, int right, int pivotIndex) {
        Comparator comparator = new PersonComparator();

        T pivotValue = items[pivotIndex];

        swap(items, pivotIndex, right);

        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (comparator.compare(items[i], pivotValue) < 0) {
                swap(items, i, storeIndex);
                storeIndex += 1;
            }
        }

        swap(items, storeIndex, right);
        return storeIndex;
    }
}
