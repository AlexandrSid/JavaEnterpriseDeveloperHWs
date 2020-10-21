package com.alexsid.lesson5.petcatalogue;

/**
 * 1. Разработать программу – картотеку домашних животных.
 * У каждого животного есть уникальный идентификационный номер,
 * кличка, хозяин (объект класс Person с полями – имя, возраст, пол), вес.
 * <p>
 * Реализовать:
 * <p>
 * • метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
 * <p>
 * • поиск животного по его кличке (поиск должен быть эффективным)
 * <p>
 * • изменение данных животного по его идентификатору
 * <p>
 * • вывод на экран списка животных в отсортированном порядке.
 * Поля для сортировки – хозяин(имя, по возрастанию), если имена хозяев одинаковые - кличка животного.
 * Если и имя хозяина и кличка животного одинаковые - раньше вывести животное, у которого больше вес
 */

import java.util.*;

public class PetCatalogueIterate implements PetCatalogue{
    //вообще это требование об эфективном поиске по имени приводит в замешательство,
    // map с ключём по имени не подходит так как никто не гарантирует уникальности имён,
    //а так хоть питомцы с одинаковыми именами будут рядом лежать.(Стивен Кинг какой-то получился)
    //UPD Был HashSet стал TreeSet, чтобы не мучаться с выводом по порядку.
    //upd2; treeset не пересобирается при изменении элементов, только при добавлении/удалении,
    //поэтому будет ArrayList и сортировка по имени, для эффективного поиска по имени.
    //upd3 для поиска нужен объет, а есть только свойство объекта
    //поэтому будет вот такой франкенштейн: Map<String, List<Pet>>
    private final Map<String, List<Pet>> pets;

    public PetCatalogueIterate() {
        this.pets = new HashMap<>();
    }

    public List<Pet> addPet(Pet pet) throws DublicatePetException {
        if (pets.getOrDefault(pet.getName(), Collections.emptyList()).contains(pet))
            throw new DublicatePetException(pet);

        List<Pet> sameNamePets = pets.getOrDefault(pet.getName(), new ArrayList<>());
        sameNamePets.add(pet);
        return pets.put(pet.getName(), sameNamePets);
    }

    public List<Pet> findByName(String name) {
        return pets.get(name);
    }

    //выглядит ужасно, но просили оптимизировать поиск по имени, а это обратная сторона.
    // Предположу, что дальше будет задание переписать это с использованием БД
    public boolean changePetName(int id, String newName) {
        Pet targetPet = getPetById(id);
        if (targetPet == null) return false;
        List<Pet> node = pets.get(targetPet.getName());
        node.remove(targetPet);
        targetPet.setName(newName);
        addPet(targetPet);
        return true;
    }

    public boolean changePetOwner(int id, Person newOwner) {
        Pet targetPet = getPetById(id);
        if (targetPet == null) return false;
        targetPet.setOwner(newOwner);
        return true;
    }

    //сомневаюсь что метод с такой логикой должен быть, он был бы уместен как приватный метод класса Pet, и соответственно работал исходя из внутренней логики
    public boolean changePetWeight(int id, float newWeight) {
        Pet targetPet = getPetById(id);
        if (targetPet == null) return false;
        targetPet.setWeight(newWeight);
        return true;
    }

    private Pet getPetById(int id) {
        List<Pet> allPetsList = new ArrayList<>();
        for (List<Pet> list : pets.values()) {//напомню, что value - это List<Pet>
            allPetsList.addAll(list);
        }
        Pet targetPet = null;
        for (Pet p : allPetsList) {
            if (p.getId() == id) targetPet = p;
        }
        return targetPet;
    }

    public void showAllPets() {
        List<Pet> petsLocal = new ArrayList<>();
        for (List<Pet> list : pets.values()) {
            petsLocal.addAll(list);
        }
        Collections.sort(petsLocal);
        for (Pet pet:petsLocal) System.out.println(pet);
    }

}
