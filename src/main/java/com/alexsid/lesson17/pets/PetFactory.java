package com.alexsid.lesson17.pets;

import com.alexsid.lesson17.Person;

import java.util.UUID;

public class PetFactory {

    private static PetFactory factory = new PetFactory();

    public Pet createPet(String petType, Person owner, String name, Double weight) {
        int newId =
                UUID.randomUUID().clockSequence();
        return switch (petType.toLowerCase()) {
            case "cat" -> new Cat(newId, name, owner, weight);
            case "dog" -> new Dog(newId, name, owner, weight);
            case "parrot" -> new Parrot(newId, name, owner, weight);
            default -> new Exotic(newId, name, owner, weight);
        };
    }

    private PetFactory() {
    }

    public static PetFactory getInstance() {
        return factory;
    }


}
