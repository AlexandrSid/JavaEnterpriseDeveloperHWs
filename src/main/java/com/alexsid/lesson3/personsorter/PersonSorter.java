package main.java.com.alexsid.lesson3.personsorter;

public interface PersonSorter {
    void sort(Person[] persons);

    default <T> void swap(T[] items, int left, int right)//Для лучшей читаетмости
    {
        if (left != right)
        {
            T temp = items[left];
            items[left] = items[right];
            items[right] = temp;
        }
    }
}
