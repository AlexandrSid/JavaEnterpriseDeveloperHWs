package com.alexsid.lesson21;

import com.alexsid.lesson17.Person;
import com.alexsid.lesson17.PetCatalog;
import com.alexsid.lesson17.PetCatalogFacade;
import com.alexsid.lesson17.pets.CheaterPet;
import com.alexsid.lesson17.pets.Pet;
import com.alexsid.lesson17.pets.PetFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 *  Изменить приложение для сериализации данных.
 *  Сохранять объект в JSON с помощью одной из библиотек сериализации.
 *
 *  не понял о каком приложении из выполненных ДЗ идёт речь,
 *  поэтому напишу JSON сериализатор для модели данных картотеки домашних животных их предыдущего блока
 *  на основе Jackson ObjectMapper
 */
public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        PetFactory factory = PetFactory.getInstance();
        PetCatalogFacade defaultCatalogFacade = new PetCatalogFacade(PetCatalog.getInstance());
        Person jean = new Person("Jean", 19, Person.Sex.FEMALE);
        defaultCatalogFacade.addPetToCatalog(factory.createPet("cat", jean, "Flare", 3.4));

        ObjectMapper mapper = new ObjectMapper();
        Pet flare = defaultCatalogFacade.findPetByName("Flare");
        String json = mapper.writeValueAsString(flare);
        System.out.println(json);

        Pet petFromJson = mapper.readValue(json, CheaterPet.class);
        System.out.println(petFromJson.equals(flare));


    }
}
