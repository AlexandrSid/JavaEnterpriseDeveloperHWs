package main.java.com.alexsid.lesson1517;

import main.java.com.alexsid.lesson1517.pets.DuplicatedPetException;
import main.java.com.alexsid.lesson1517.pets.Pet;
import main.java.com.alexsid.lesson1517.pets.PetComparator;

import java.util.List;

public class PetCatalogFacade {

    private PetCatalog petCatalog;
    private PetComparator comparator;
    private DuplicatedPetExceptionHandler exceptionHandler = new DuplicatedPetExceptionHandler() {
        @Override
        public void process(DuplicatedPetException e) {
            System.out.println("exception " + e + "catched");
        }
    };

    public PetCatalogFacade(PetCatalog petCatalog) {
       this(petCatalog, new PetComparator());
    }

    public PetCatalogFacade(PetCatalog petCatalog, PetComparator comparator) {
        this.petCatalog = petCatalog;
        this.comparator = comparator;
    }

    //facade methods
    public void addPetToCatalog(Pet pet) {
        try {
            petCatalog.addPet(pet);
        } catch (DuplicatedPetException e) {//сюда можно подсовывать какой-нибудь обработчик исключений в рамках реализации паттерна Стратегия
            exceptionHandler.process(e);
        }
    }

    public Pet findPetByName(String name) {
        List<Pet> petByName = petCatalog.findPetByName(name);
        if (petByName == null || petByName.isEmpty()) {
            System.out.println("No pet with such name");
            return null;
        }
        if(petByName.size()>1){
            System.out.println("more than one pet with such name");
            System.out.println(petByName);
        }
        return petByName.get(0);
    }

    public void changePetNameByID(int id, String name) {
        Pet target = petCatalog.getByID(id);
        if (target == null)return;//не красиво
        target.setName(name);
        petCatalog.removeByID(id);
        petCatalog.addPet(target);
    }

    public void changePetOwnerByID(int id, Person owner) {
        Pet target = petCatalog.getByID(id);
        if (target == null)return;
        target.setOwner(owner);
        petCatalog.removeByID(id);
        petCatalog.addPet(target);
    }

    public void changePetWeightByID(int id, double newWeight) {
        Pet target = petCatalog.getByID(id);
        if (target == null)return;
        target.setWeight(newWeight);
        petCatalog.removeByID(id);
        petCatalog.addPet(target);
    }

    public void showAllPetsFromCatalog() {
        petCatalog.showAllPetsSorted(comparator);
    }

    public void setExceptionHandler(DuplicatedPetExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }
}
