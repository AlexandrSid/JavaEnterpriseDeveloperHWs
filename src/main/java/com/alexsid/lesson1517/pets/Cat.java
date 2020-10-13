package main.java.com.alexsid.lesson1517.pets;

import main.java.com.alexsid.lesson1517.Person;

import java.util.Objects;

public class Cat extends AbstractPet {
    private final PetType type = PetType.CAT;

    protected Cat(int id, String name, Person owner, double weight) {
        super(id, name, owner, weight);
    }

    @Override
    public PetType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cat)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getId(), getOwner(), getWeight());
    }
}