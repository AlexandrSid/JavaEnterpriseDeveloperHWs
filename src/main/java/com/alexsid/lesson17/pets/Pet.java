package com.alexsid.lesson17.pets;

import com.alexsid.lesson17.Person;

public interface Pet {
    long getId();
    Person getOwner();
    PetType getType();
    double getWeight();
    String getName();
    void setName(String name);
    void setOwner(Person owner);
    void setWeight(double weight);


}
