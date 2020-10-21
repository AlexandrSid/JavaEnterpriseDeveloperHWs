package com.alexsid.lesson12;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Employee head = new Employee("Василий Иванович Босов", 54, null);
        Organization organization = new Organization(head, Collections.emptyList());
        Department wideSpecializedWorkers = new Department(Collections.emptyList(), null);//кольцевые ссылки, так и просится ошибится
        organization.departments().add(wideSpecializedWorkers);
        Employee vasia = new Employee("Просто Вася", 41, wideSpecializedWorkers);


        //VM option -XX:+ShowCodeDetailsInExceptionMessages
        Employee manager = organization.departments().stream().findFirst().get().manager();

    }
}