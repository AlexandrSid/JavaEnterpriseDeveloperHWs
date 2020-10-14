package main.java.com.alexsid.lesson12;

import java.util.Collection;

public record Organization (Employee head, Collection<Department> departments){
}
