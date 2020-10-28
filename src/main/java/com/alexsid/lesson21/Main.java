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

import java.io.IOException;

/**
 *  Изменить приложение для сериализации данных.
 *  Сохранять объект в JSON с помощью одной из библиотек сериализации.
 *
 *  не понял о каком приложении из выполненных ДЗ идёт речь,
 *  поэтому напишу JSON сериализатор для модели данных картотеки домашних животных их предыдущего блока
 *  на основе Jackson ObjectMapper
 */
public class Main {
    public static void main(String[] args) throws IOException {
        PetFactory factory = PetFactory.getInstance();
        PetCatalogFacade defaultCatalogFacade = new PetCatalogFacade(PetCatalog.getInstance());
        Person jean = new Person("Jean", 19, Person.Sex.FEMALE);
        defaultCatalogFacade.addPetToCatalog(factory.createPet("Racccooon", jean, "Bandit", 6.6));

        PetSerializer serializer = new PetSerializer();

        ObjectMapper mapper = new ObjectMapper();
        Pet bandit = defaultCatalogFacade.findPetByName("Bandit");

        serializer.serialize(bandit);
        long targetId = bandit.getId();
        Pet deserialized = serializer.deserialize(targetId);

        System.out.println(bandit);
        System.out.println(deserialized);
        System.out.println(bandit.equals(deserialized));
        System.out.println(deserialized.equals(bandit));

    }
}
