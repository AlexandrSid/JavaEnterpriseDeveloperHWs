package main.java.com.alexsid.lesson4;

import java.util.Collection;
import java.util.HashSet;

/**
 * 2. Создать класс ObjectBox, который будет хранить коллекцию Object.
 * <p>
 * • У класса должен быть метод addObject, добавляющий объект в коллекцию.
 * <p>
 * • У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
 * <p>
 * • Должен быть метод dump, выводящий содержимое коллекции в строку.
 */
public class ObjectBox  {
    protected Collection collection = new HashSet();

    public void addObject(Object o) {
        collection.add(o);
    }

    public boolean deleteObject(Object o) {
        boolean remove = collection.remove(o);
        return remove;
    }

    public void dump(){
        System.out.println(collection);
    }
}
