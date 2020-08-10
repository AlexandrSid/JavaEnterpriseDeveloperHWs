package main.java.com.alexsid.lesson5.petcatalogue;

import java.util.List;

public interface PetCatalogue {
    public List<Pet> addPet(Pet pet) throws DublicatePetException;

    public List<Pet> findByName(String name);

    public boolean changePetName(int id, String newName);

    public boolean changePetOwner(int id, Person newOwner);

    public boolean changePetWeight(int id, float newWeight);

    public void showAllPets();
}
