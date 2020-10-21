package com.alexsid.lesson5.petcatalogue;

public class DublicatePetException extends RuntimeException{
    public DublicatePetException(Pet pet) {
        super(pet.toString());
    }
}
