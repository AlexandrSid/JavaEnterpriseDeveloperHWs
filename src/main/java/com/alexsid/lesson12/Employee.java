package main.java.com.alexsid.lesson12;

public record Employee (String name, int age, Department dpt) {
    public Employee{
        dpt.employees().add(this);
    }
}
