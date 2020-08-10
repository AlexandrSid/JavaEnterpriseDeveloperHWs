package main.java.com.alexsid.lesson3.personsorter;

import java.util.Comparator;

public class BubblePersonSorter implements PersonSorter {
    @Override
    public void sort(Person[] persons) {
        Comparator<Person> comparator = new PersonComparator();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < persons.length; i++) {
                if (comparator.compare(persons[i - 1], persons[i]) > 0) {
                    swap(persons, i - 1, i);
                    swapped = true;
                }
            }
        } while (swapped != false);
    }
}
