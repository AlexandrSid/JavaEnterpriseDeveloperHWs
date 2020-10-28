package com.alexsid.lesson21;


import com.alexsid.lesson17.Person;
import com.alexsid.lesson17.pets.Pet;

import java.util.List;

public class JsonFacade {
    public static final JsonPetCatalog catalog = JsonPetCatalog.getInstance();

    public static void addPetToCatalog(Pet pet){
        catalog.saveToCatalog(pet);
    }

    public static Pet getPetById(long id){
        return catalog.getFromCatalog(id);
    }

    public static List<Pet> getAllPets(){
        return catalog.getAllPets();
    }

    public static Pet updatePetById(long id, String name){
        Pet fromCatalog = catalog.getFromCatalog(id);
        fromCatalog.setName(name);
        catalog.removeFromCatalog(id);
        catalog.saveToCatalog(fromCatalog);
        return fromCatalog;
    }
    public static Pet updatePetById(long id, Person owner){
        Pet fromCatalog = catalog.getFromCatalog(id);
        fromCatalog.setOwner(owner);
        catalog.removeFromCatalog(id);
        catalog.saveToCatalog(fromCatalog);
        return fromCatalog;
    }
    public static Pet updatePetById(long id, Double weight){
        Pet fromCatalog = catalog.getFromCatalog(id);
        fromCatalog.setWeight(weight);
        catalog.removeFromCatalog(id);
        catalog.saveToCatalog(fromCatalog);
        return fromCatalog;
    }

    public static boolean deletePet(long id){
        return catalog.removeFromCatalog(id);
    }

    public static Pet findByName(String name){
        return catalog.findByName(name);
    }
}
