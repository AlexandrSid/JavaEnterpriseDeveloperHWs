package main.java.com.alexsid.lesson1517;
//"lesson15-17" идея ругалась, какая договорённость на использование символов в именах пакетов?

import main.java.com.alexsid.lesson1517.pets.DuplicatedPetException;
import main.java.com.alexsid.lesson1517.pets.Pet;
import main.java.com.alexsid.lesson1517.pets.PetComparator;

import java.util.*;

/**
 * 1. Разработать программу – картотеку домашних животных.
 * У каждого животного есть уникальный идентификационный номер, кличка, хозяин(объект класс Person с полями – имя, возраст, пол), вес.
 * <p>
 * Реализовать:
 * • метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
 * • поиск животного по его кличке (поиск должен быть эффективным)
 * • изменение данных животного по его идентификатору
 * • вывод на экран списка животных в отсортированном порядке. Поля для сортировки – хозяин(имя, по возрастанию),
 * если имена хозяев одинаковые - кличка животного. Если и имя хозяина и кличка животного одинаковые
 * - раньше вывести животное, у которого больше вес
 * <p>
 * <p>
 * 15.1. Применить один из порождающих паттернов для приложения “Картотека домашних животных”
 * 15.2. Написать собственную реализацию паттерна Singleton
 * 16.1. Применить один из структурных паттернов для приложения «Картотека домашних животных»
 * 17.1. Применить один из поведенческих паттернов для приложения «Картотека домашних животных»
 */

//собссно сам каталог, поскольку должен быть в единственном экземпляре то он и будет синглтоном,
//а поскольку вляется ключевым элементом приложения, то БЕЗ ленивой инициализации с проверками безопасности многопоточности
public class PetCatalog {
    private static PetCatalog instance = new PetCatalog();
    private Map<String, List<Pet>> catalog = new HashMap<>();

    private PetCatalog() {

    }

    public static PetCatalog getInstance() {
        return instance;
    }


    public void addPet(Pet pet) throws DuplicatedPetException {
        String name = pet.getName();
        List<Pet> petsByName = findPetByName(name);
        if ((petsByName != null) && (!petsByName.isEmpty()) && petsByName.contains(pet))
            throw new DuplicatedPetException(pet.toString() + " already exists in the catalog");
        if (petsByName == null || petsByName.isEmpty()) petsByName = new ArrayList<>();
        petsByName.add(pet);
        catalog.put(name, petsByName);
    }

    public Pet removeByID(int id) {
        Pet target = getByID(id);
        for (List<Pet> namesakes : catalog.values()){
            namesakes.stream().filter(pet -> pet.getId() == id).findFirst().ifPresent(namesakes::remove);
        }
        return target;
    }

    public List<Pet> findPetByName(String name) {
        return catalog.get(name);
    }

    public Pet getByID(int id) {
        try {
            return catalog.values().stream().flatMap(Collection::stream).filter(p -> p.getId() == id).findFirst().get();
        } catch (NoSuchElementException e) {
            System.out.println("Нет такого id в каталоге");
        }
        return null;
    }

    public void showAllPetsSorted(PetComparator comparator) {
        catalog.values().stream().flatMap(Collection::stream).sorted(comparator).forEach(System.out::println);
    }
}
