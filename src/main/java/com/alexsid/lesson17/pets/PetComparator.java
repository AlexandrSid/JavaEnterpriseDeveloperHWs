package com.alexsid.lesson17.pets;

import java.util.Comparator;

public class PetComparator implements Comparator<Pet> {

    @Override
    public int compare(Pet o1, Pet o2) {
        int own = o1.getOwner().getName().compareTo(o2.getOwner().getName());
        if (own != 0) return own;
        int i = o1.getName().compareTo(o2.getName());
        if (i != 0) return i;
        return Double.compare(o2.getWeight(), o1.getWeight());

    }
}
