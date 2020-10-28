package com.alexsid.lesson21;

import com.alexsid.lesson17.pets.CheaterPet;
import com.alexsid.lesson17.pets.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PetSerializer {
    ObjectMapper objectMapper = new ObjectMapper();
    String targetDirectory = "src/main/resources/pet-catalog-json/";

    public void serialize(Pet pet) {
        String path = String.format("%s%d.json", targetDirectory, pet.getId());
        try {
            objectMapper.writeValue(new File(path), pet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pet deserialize(long id) {
        String path = String.format("%s%d.json", targetDirectory, id);
        try {
            return objectMapper.readValue(new File(path), CheaterPet.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CheaterPet();
    }

    public Pet deserialize(Path path) {
        try {
            return objectMapper.readValue(path.toFile(), CheaterPet.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CheaterPet();
    }

}
