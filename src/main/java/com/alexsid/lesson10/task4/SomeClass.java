package main.java.com.alexsid.lesson10.task4;

public class SomeClass implements Worker {
    static {
        System.out.println("SomeClass loaded with " + SomeClass.class.getClassLoader().getName());
    }
    @Override
    public void doWork() {
    }
}
