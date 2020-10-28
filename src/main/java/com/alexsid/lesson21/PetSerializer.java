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

    public void serialize(Pet pet) throws IOException {
        String path = String.format("%s%d.json", targetDirectory, pet.getId());
        objectMapper.writeValue(new File(path), pet);
    }

    public Pet deserialize(long id) throws IOException {
        String path = String.format("%s%d.json", targetDirectory, id);
        Pet result = objectMapper.readValue(new File(path), CheaterPet.class);
        return result;
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
