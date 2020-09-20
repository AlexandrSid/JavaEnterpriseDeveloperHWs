package main.java.com.alexsid.lesson12;

import java.util.List;

public record Department (List<Employee> employees, Employee manager){

    public Department{
        employees.add(manager);
    }
}
