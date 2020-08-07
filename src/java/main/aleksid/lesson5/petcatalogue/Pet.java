package java.main.aleksid.lesson5.petcatalogue;

import java.util.Objects;

/**
 * 1. Разработать программу – картотеку домашних животных.
 *   У каждого животного есть уникальный идентификационный номер,
 *   кличка, хозяин (объект класс Person с полями – имя, возраст, пол), вес.
 *
 * Реализовать:
 *
 * • метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
 *
 * • поиск животного по его кличке (поиск должен быть эффективным)
 *
 * • изменение данных животного по его идентификатору
 *
 * • вывод на экран списка животных в отсортированном порядке.
 *   Поля для сортировки – хозяин(имя, по возрастанию), если имена хозяев одинаковые - кличка животного.
 *   Если и имя хозяина и кличка животного одинаковые - раньше вывести животное, у которого больше вес
 *   */

public class Pet {
    private static int lastid = 0;

    private final int id;
    private Person owner;
    private float weight;

    public Pet(Person owner, float weight) {
        this.id = lastid++;
        this.owner = owner;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public Person getOwner() {
        return owner;
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id == pet.id &&
                Float.compare(pet.weight, weight) == 0 &&
                Objects.equals(owner, pet.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, weight);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", owner=" + owner +
                ", weight=" + weight +
                '}';
    }
}
