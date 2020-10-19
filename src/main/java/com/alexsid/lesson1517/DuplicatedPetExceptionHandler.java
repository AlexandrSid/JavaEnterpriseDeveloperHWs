package main.java.com.alexsid.lesson1517;

import main.java.com.alexsid.lesson1517.pets.DuplicatedPetException;

public interface DuplicatedPetExceptionHandler {//strategy pattern
    void process(DuplicatedPetException e);
}
