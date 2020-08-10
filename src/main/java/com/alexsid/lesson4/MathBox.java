package main.java.com.alexsid.lesson4;

import java.lang.annotation.Documented;
import java.util.Arrays;
import java.util.List;
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
public class MathBox<T extends Number> {

    private final List<T> collection;

    private final NumberUtils numberUtils = new NumberUtils();


    public MathBox(T[] argNumbers) {
        this.collection = Arrays.asList(argNumbers);//внутри это будет ArrayList, не уверен, что стоит это явно прописывать.
    }

    public T summator() {
        T result = (T) numberUtils.getZero();
//        for (Double  number : collection){
//            result += number;
//        }
        return result;

    }

    public void splitter(Double devider) {
        for (T d : collection) {
            
        }
    }

    public boolean removeInteger(Integer integer) {
        return false;
    }

    @Override
    public int hashCode() {
        return collection.stream().mapToInt(Number::hashCode).reduce(0, (a, b) -> a = b);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {
        MathBox mathBox = new MathBox(new Integer[]{1, 2, 3, 4, 5, 5, 3, 6, 2});
        mathBox.splitter(2.0);
    }
}

