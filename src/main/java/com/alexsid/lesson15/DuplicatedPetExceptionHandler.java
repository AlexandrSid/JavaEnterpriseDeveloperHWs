package com.alexsid.lesson15;

import com.alexsid.lesson15.pets.DuplicatedPetException;

public interface DuplicatedPetExceptionHandler {//strategy pattern
    void process(DuplicatedPetException e);
}
