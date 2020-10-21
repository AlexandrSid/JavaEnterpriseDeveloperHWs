package com.alexsid.lesson5.petcatalogue;

import java.util.List;

public interface PetCatalogue {
    List<Pet> addPet(Pet pet) throws DublicatePetException;

    List<Pet> findByName(String name);

    boolean changePetName(int id, String newName);

    boolean changePetOwner(int id, Person newOwner);

    boolean changePetWeight(int id, float newWeight);

    void showAllPets();
}
