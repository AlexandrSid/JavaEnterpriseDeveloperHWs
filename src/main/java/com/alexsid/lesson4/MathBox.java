package com.alexsid.lesson4;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 1. Написать класс MathBox, реализующий следующий функционал:
 * <p>
 * • Конструктор на вход получает массив Number. Элементы не могут повторяться.
 * Элементы массива внутри объекта раскладываются в подходящую коллекцию (выбрать самостоятельно).
 * <p>
 * • Существует метод summator, возвращающий сумму всех элементов коллекции.
 * <p>
 * • Существует метод splitter, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель,
 * являющийся аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
 * <p>
 * • Необходимо правильно переопределить методы toString, hashCode, equals, чтобы можно было использовать MathBox
 * для вывода данных на экран и хранение объектов этого класса в коллекциях (например, hashMap). Выполнение контракта обязательно!
 * <p>
 * • Создать метод, который получает на вход Integer и если такое значение есть в коллекции, удаляет его.
 */
public class MathBox<T extends Number> extends ObjectBox {//параметризация <? extends Number> невозможна, Параметризация <T extends Number> ограничивает возможность использования одного типа для одной коробки

    private Set<T> collection;
    private final Calculator calculator;

    public MathBox(final T[] argNumbers) {
        collection = Arrays.stream(argNumbers).collect(Collectors.toSet());//Просили же всё что можно через StreamAPI :D
        calculator = CalculatorProvider.getOne(argNumbers[0].getClass() );//ничего лучше не придумал ((
        //вообще всё это какая-то акробатика, а не архитектура, как мне кажется.

    }

    public T summator() {
        return collection.stream().reduce((a,b) -> (T)calculator.add(a, b)).get();
    }

    public void splitter(T divider) {
        collection = collection.stream().map(n -> (T)calculator.divide(n, divider)).collect(Collectors.toSet());
    }

    public void removeInteger(Integer integer) {
        collection = collection.stream().filter(n -> MyNumberUtils.notEqualsInt(n, integer)).collect(Collectors.toSet());
    }

    @Override
    public int hashCode() {
        return collection.stream().mapToInt(Number::hashCode).reduce(0, Integer::sum);//Хэш матбокса как сумма хэшей содержащихся в нём чисел
    }

    @Override
    public boolean equals(Object anotherBox) {
        if (anotherBox.getClass().isInstance(MathBox.class)) {
            return collection.equals(((MathBox)anotherBox).collection);
        }
        return false;
    }

    @Override
    public String toString() {
        return collection.toString();
    }

    @Override
    public void addObject(Object o) {
        if (o instanceof Number){
            collection.add((T) o);}
        else{
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deleteObject(Object o) {
        collection.remove(o);
        return super.deleteObject(o);
    }

    @Override
    public void dump() {
        super.dump();
    }

//    private class WrongArgumentException extends RuntimeException {

//    }

    interface Calculator<T>{
        T add(T n1, T n2);
        T divide(T n, T divider);
    }
}

