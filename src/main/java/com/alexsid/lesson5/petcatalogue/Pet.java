package com.alexsid.lesson5.petcatalogue;

import java.util.Objects;


public class Pet implements Comparable<Pet> {
    private static int lastid = 0;

    private final int id;
    private String name;
    private Person owner;
    private float weight;

    public Pet(String name, Person owner, float weight) {
        this.id = lastid++;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id == pet.id &&
                Float.compare(pet.weight, weight) == 0 &&
                Objects.equals(name, pet.name) &&
                Objects.equals(owner, pet.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);//чтобы поиск по имени был эффективным
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Pet o) {
        int own = this.owner.getName().compareTo(o.owner.getName());
        if (own != 0) return own;
        int i = name.compareTo(o.getName());
        if (i != 0) return i;
        return Float.compare(o.getWeight(), this.weight);
    }
}
