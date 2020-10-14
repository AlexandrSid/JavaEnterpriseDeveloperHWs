package main.java.com.alexsid.lesson10.tasks13;

import java.util.Arrays;


public class SorterImpl implements Sorter {

    @Override
    public void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }

    @Override
    public void standardSort(int[] arr) {
        Arrays.sort(arr);
    }
}
