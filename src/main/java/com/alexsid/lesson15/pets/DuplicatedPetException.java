package com.alexsid.lesson15.pets;

public class DuplicatedPetException extends RuntimeException{
    {
        System.out.println("DuplicatedPetException created");
    }

    public DuplicatedPetException(String message) {
        super(message);
    }
}
