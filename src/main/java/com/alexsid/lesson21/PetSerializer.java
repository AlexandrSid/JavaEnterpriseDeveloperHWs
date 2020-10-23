package com.alexsid.lesson21;

import com.alexsid.lesson17.pets.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class PetSerializer {

    public void serialize(Pet pet) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("target/pet.json"), pet);
    }

    public Pet deserialize() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Pet result = objectMapper.readValue(new File("target/pet.json"), Pet.class);
        return result;
    }
}
