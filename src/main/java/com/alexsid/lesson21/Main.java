package com.alexsid.lesson21;

import com.alexsid.lesson17.Person;
import com.alexsid.lesson17.pets.Pet;
import com.alexsid.lesson17.pets.PetFactory;

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
    public static void main(String[] args) {
        PetFactory factory = PetFactory.getInstance();

        Person jean = new Person("Jean", 19, Person.Sex.FEMALE);
        Person crism = new Person("Cris", 22, Person.Sex.MALE);
        Person mike = new Person("Mike", 20, Person.Sex.MALE);
        Person helen = new Person("Helen", 22, Person.Sex.FEMALE);
        Person sam = new Person("Sam", 21, Person.Sex.MALE);
        Person july = new Person("July", 18, Person.Sex.FEMALE);
        Person crisf = new Person("Cris", 19, Person.Sex.FEMALE);

        JsonFacade.addPetToCatalog(factory.createPet("cat", jean, "Flare", 3.4));
        JsonFacade.addPetToCatalog(factory.createPet("cat", jean, "Fluffy", 4.3));
        JsonFacade.addPetToCatalog(factory.createPet("cat", jean, "Foxy", 2.9));
        JsonFacade.addPetToCatalog(factory.createPet("cat", jean, "Fred", 4.9));
        JsonFacade.addPetToCatalog(factory.createPet("parrot", crism, "Twetty", 0.2));
        JsonFacade.addPetToCatalog(factory.createPet("dog", crism, "Lucky", 9.2));
        JsonFacade.addPetToCatalog(factory.createPet("Snake", mike, "Serpenty", 5.0));
        JsonFacade.addPetToCatalog(factory.createPet("dOg", helen, "Bolt", 17.7));
        JsonFacade.addPetToCatalog(factory.createPet("doG", sam, "Bolt", 14.7));
        JsonFacade.addPetToCatalog(factory.createPet("doG", sam, "Buffy", 13.2));
        JsonFacade.addPetToCatalog(factory.createPet("Racccooon", sam, "Bandit", 6.6));
        JsonFacade.addPetToCatalog(factory.createPet("Owl", july, "Khrias", 0.6));
        JsonFacade.addPetToCatalog(factory.createPet("Owl", july, "Khrum", 0.5));
        JsonFacade.addPetToCatalog(factory.createPet("Owl", july, "Willow", 0.4));
        JsonFacade.addPetToCatalog(factory.createPet("Owl", july, "Fred", 3.4));
        JsonFacade.addPetToCatalog(factory.createPet("Cat", july, "Murk", 4.4));
        JsonFacade.addPetToCatalog(factory.createPet("Lizard", crisf, "Pancake", 1.4));


        System.out.println(JsonFacade.getAllPets());
        Pet pet = JsonFacade.findByName("Bolt");
        System.out.println(pet);


    }
}
