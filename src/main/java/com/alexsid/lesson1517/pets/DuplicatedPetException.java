package com.alexsid.lesson1517.pets;

public class DuplicatedPetException extends RuntimeException{
    {
        System.out.println("DuplicatedPetException created");
    }

    public DuplicatedPetException(String message) {
        super(message);
    }
}
