package com.alexsid.lesson15.pets;

import com.alexsid.lesson15.Person;

public interface Pet {
    int getId();
    Person getOwner();
    PetType getType();
    double getWeight();
    String getName();
    void setName(String name);
    void setOwner(Person owner);
    void setWeight(double weight);


}
