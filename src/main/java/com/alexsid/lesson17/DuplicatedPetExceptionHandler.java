package com.alexsid.lesson17;

import com.alexsid.lesson17.pets.DuplicatedPetException;

public interface DuplicatedPetExceptionHandler {//strategy pattern
    void process(DuplicatedPetException e);
}
