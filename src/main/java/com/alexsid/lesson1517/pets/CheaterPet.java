package com.alexsid.lesson1517.pets;

import com.alexsid.lesson1517.Person;

public class CheaterPet extends AbstractPet {//полностью открытая реализация Pet с контруктором по-умолчанию (чтобы с рефлесией не заморачиваться)
    public CheaterPet(int id, PetType type, String name, Person owner, double weight) {
        super(id, name, owner, weight);
        this.type = type;
    }

    public CheaterPet() {
        super(0, null, null, 0.0);
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }
}
