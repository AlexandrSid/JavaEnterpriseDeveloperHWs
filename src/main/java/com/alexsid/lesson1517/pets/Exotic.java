package com.alexsid.lesson1517.pets;

import com.alexsid.lesson1517.Person;

import java.util.Objects;

public class Exotic extends AbstractPet {
    private final PetType type = PetType.EXOTIC;

    protected Exotic(int id, String name, Person owner, double weight) {
        super(id, name, owner, weight);
    }

    @Override
    public PetType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exotic)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getId(), getOwner(), getWeight());
    }
}

