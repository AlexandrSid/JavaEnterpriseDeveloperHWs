package com.alexsid.lesson21;

import com.alexsid.lesson17.pets.CheaterPet;
import com.alexsid.lesson17.pets.Pet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonPetCatalog {
    private static final JsonPetCatalog instance = new JsonPetCatalog();
    private PetSerializer serializer = new PetSerializer();
    private String catalogPath = "src/main/resources/pet-catalog-json";

    public static JsonPetCatalog getInstance() {
        return instance;
    }

    private JsonPetCatalog() {
    }

    public long saveToCatalog(Pet pet) {
        serializer.serialize(pet);
        return pet.getId();
    }

    public Pet getFromCatalog(long id) {
        return serializer.deserialize(id);
    }

    public boolean removeFromCatalog(long id) {
        String path = String.format("%s/%d.json", catalogPath, id);
        return Paths.get(path).toFile().delete();
    }

    public List<Pet> getAllPets() {
        try (Stream<Path> stream = Files.walk(Paths.get(catalogPath))) {
            return stream
                    .filter(Files::isRegularFile)
                    .map(f -> serializer.deserialize(f))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    public Pet findByName(String name) {
        try (Stream<Path> stream = Files.walk(Paths.get(catalogPath))) {
            return stream
                    .filter(Files::isRegularFile)
                    .map(f -> serializer.deserialize(f))
                    .filter(pet -> pet.getName().equals(name))
                    .findFirst()
                    .orElseGet(CheaterPet::new);
//хотел сделать .map(Files::readLine) и далее парсить json строку на предмет имени,
// но кидало IOException, который непонятно было почему не ловится
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CheaterPet();
    }

    public void removeAll() {
        try (Stream<Path> stream = Files.walk(Paths.get(catalogPath))) {
            stream.map(Path::toFile).forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
