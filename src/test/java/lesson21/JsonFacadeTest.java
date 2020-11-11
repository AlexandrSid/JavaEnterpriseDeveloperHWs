package lesson21;

import com.alexsid.lesson17.Person;
import com.alexsid.lesson17.pets.Pet;
import com.alexsid.lesson17.pets.PetFactory;
import com.alexsid.lesson21.JsonFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonFacadeTest {
    PetFactory factory = PetFactory.getInstance();

    @Before
    public void setUp() throws Exception {

        Person jean = new Person("Jean", 19, Person.Sex.FEMALE);
        Person cris_m = new Person("Cris", 22, Person.Sex.MALE);
        Person mike = new Person("Mike", 20, Person.Sex.MALE);
        Person helen = new Person("Helen", 22, Person.Sex.FEMALE);
        Person sam = new Person("Sam", 21, Person.Sex.MALE);
        Person july = new Person("July", 18, Person.Sex.FEMALE);
        Person cris_f = new Person("Cris", 19, Person.Sex.FEMALE);

        JsonFacade.addPetToCatalog(factory.createPet("cat", jean, "Flare", 3.4));
        JsonFacade.addPetToCatalog(factory.createPet("cat", jean, "Fluffy", 4.3));
        JsonFacade.addPetToCatalog(factory.createPet("cat", jean, "Foxy", 2.9));
        JsonFacade.addPetToCatalog(factory.createPet("cat", jean, "Fred", 4.9));
        JsonFacade.addPetToCatalog(factory.createPet("parrot", cris_m, "Twetty", 0.2));
        JsonFacade.addPetToCatalog(factory.createPet("dog", cris_m, "Lucky", 9.2));
        JsonFacade.addPetToCatalog(factory.createPet("Snake", mike, "Serpenty", 5.0));
        JsonFacade.addPetToCatalog(factory.createPet("dOg", helen, "Bolt", 17.7));
        JsonFacade.addPetToCatalog(factory.createPet("doG", sam, "Bolt", 14.7));
        JsonFacade.addPetToCatalog(factory.createPet("doG", sam, "Buffy", 13.2));
        JsonFacade.addPetToCatalog(factory.createPet("Racccooon", sam, "Bandit", 6.6));
        JsonFacade.addPetToCatalog(factory.createPet("Owl", july, "Khrias", 0.6));
        JsonFacade.addPetToCatalog(factory.createPet("Owl", july, "Khrum", 0.5));
        JsonFacade.addPetToCatalog(factory.createPet("Owl", july, "Willow", 0.4));
        JsonFacade.addPetToCatalog(factory.createPet("Owl", july, "Fred", 3.4));
        JsonFacade.addPetToCatalog(factory.createPet("Cat", july, "Murk", 4.4));
        JsonFacade.addPetToCatalog(factory.createPet("Lizard", cris_f, "Pancake", 1.4));
    }

    @After
    public void tearDown() throws Exception {
        JsonFacade.dropeDB();
    }


    @Test
    public void getPetById() {
        Pet testPet = factory.createPet("cat", new Person("Jean", 19, Person.Sex.FEMALE), "Foxy", 2.9);
        JsonFacade.addPetToCatalog(testPet);
        assertEquals(testPet, JsonFacade.getPetById(testPet.getId()));
    }

    @Test
    public void getAllPets() {
        int size = JsonFacade.getAllPets().size();
        assertEquals(size, 17);//число добавлений в методе setUp()
    }

    @Test
    public void updatePetById() {
        String testName = "Foxy";
        String newName = "Fixy";
        Pet testPet = factory.createPet("cat", new Person("Jean", 19, Person.Sex.FEMALE), testName, 2.9);
        JsonFacade.addPetToCatalog(testPet);
        JsonFacade.updatePetById(testPet.getId(), newName);
        assertEquals(JsonFacade.getPetById(testPet.getId()).getName(), newName);
    }


    @Test
    public void deletePet() {
        String testName = "Fixy";
        Pet testPet = factory.createPet("cat", new Person("Jean", 19, Person.Sex.FEMALE), testName, 2.9);
        JsonFacade.addPetToCatalog(testPet);
        JsonFacade.deletePet(testPet.getId());
        assertEquals(JsonFacade.findByName(testPet.getName()).getId(), 0);
    }

    @Test
    public void findByName() {
        String testName = "Foxy";
        Pet testPet = factory.createPet("cat", new Person("Jean", 19, Person.Sex.FEMALE), testName, 2.9);
        JsonFacade.addPetToCatalog(testPet);
        assertEquals(JsonFacade.findByName(testName), testPet);
    }
}