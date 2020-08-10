package main.java.com.alexsid.lesson5.petcatalogue;

public class Main {

    public static void main(String[] args) {
        PetCatalogue petCatalogue = new PetCatalogueStream();
        petCatalogue.addPet(new Pet(
                "Barsik",
                new Person("Valeria", 15, Sex.FEMALE),
                13.1f
        ));
        petCatalogue.addPet(new Pet(
                "Pushok",
                new Person("Egor", 17, Sex.MALE),
                5.7f
        ));
        petCatalogue.addPet(new Pet(
                "Egor Jr",
                new Person("Egor", 17, Sex.MALE),
                0.3f//хомяк
        ));
        petCatalogue.addPet(new Pet(
                "Pushok",
                new Person("Egor", 15, Sex.MALE),
                18.3f
        ));
        petCatalogue.addPet(new Pet(
                "Zhuchka",
                new Person("Lidia", 13, Sex.FEMALE),
                13.0f
        ));
        petCatalogue.addPet(new Pet(
                "Lucius",
                new Person("ShyCherry", 14, Sex.FEMALE),
                13.1f
        ));

        petCatalogue.showAllPets();
        System.out.println();
        petCatalogue.findByName("Pushok").stream().forEach(System.out::println);
        petCatalogue.changePetName(3, "Feather");
        petCatalogue.changePetOwner(3, new Person("JumpingRogue", 13, Sex.MALE));
        System.out.println();
        petCatalogue.showAllPets();
    }
}
