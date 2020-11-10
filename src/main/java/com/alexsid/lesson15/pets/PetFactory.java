package com.alexsid.lesson15.pets;

import com.alexsid.lesson15.Person;

public class PetFactory {

    private static PetFactory factory = new PetFactory();
    private int idKeeper = 0;

    public Pet createPet(String petType, Person owner, String name, Double weight) {
        int newId = idKeeper++;

        return switch (petType.toLowerCase()) {
            case "cat" ->    new Cat(newId, name, owner, weight);
            case "dog" ->    new Dog(newId, name, owner, weight);
            case "parrot" -> new Parrot(newId, name, owner, weight);
            default ->       new Exotic(newId, name, owner, weight);
        };
    }

    private PetFactory() {
    }

    public static PetFactory getInstance() {
        return factory;
    }


}
