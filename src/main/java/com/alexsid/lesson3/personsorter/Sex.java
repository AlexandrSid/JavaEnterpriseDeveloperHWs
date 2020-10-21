package com.alexsid.lesson3.personsorter;

public enum Sex {
    MAN(1),
    WOMAN(0);

    public int getNumerical() {
        return numerical;
    }

    private final int numerical;

    Sex(int i) {
        this.numerical = i;
    }
}
