package main.java.com.alexsid.lesson5.petcatalogue;

import java.util.*;
import java.util.stream.Collectors;

public class PetCatalogueStream implements PetCatalogue{//Unit тесты как-нибудь может в следующий раз, не здесь

    private final List<Pet> pets;

    public PetCatalogueStream() {
        this.pets = new ArrayList<>();
    }

    public List<Pet> addPet(Pet pet) throws DublicatePetException {
        if (pets.contains(pet)) {
            throw new DublicatePetException(pet);
        } else pets.add(pet);
        return pets;
    }

    public List<Pet> findByName(String name) {
        return pets.stream().filter(pet -> pet.getName().equals(name)).collect(Collectors.toList());
    }

    public boolean changePetName(int id, String newName) {
        try {
            Pet pet = pets.stream().filter(p -> p.getId() == id).findFirst().get();
            pet.setName(newName);
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean changePetOwner(int id, Person newOwner) {
        try {
            Pet pet = pets.stream().filter(p -> p.getId() == id).findFirst().get();
            pet.setOwner(newOwner);
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean changePetWeight(int id, float newWeight) {
        try {
            Pet pet = pets.stream().filter(p -> p.getId() == id).findFirst().get();
            pet.setWeight(newWeight);
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void showAllPets() {
        pets.stream().sorted().forEach(System.out::println);
    }


}

