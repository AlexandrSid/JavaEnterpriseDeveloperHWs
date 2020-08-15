package main.java.com.alexsid.lesson7;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 1. Дан массив случайных чисел. Написать программу для вычисления факториалов всех элементов массива в многопоточном режиме.
 * Использовать пул потоков для решения задачи.
 * <p>
 * Особенности выполнения:
 * <p>
 * Для данного примера использовать рекурсию - не очень хороший вариант, т.к. происходит большое выделение памяти, очень вероятен StackOverFlow. Лучше перемножать числа в простом цикле при этом создавать объект типа BigInteger
 * <p>
 * По сути, есть несколько способа решения задания:
 * <p>
 * 1) распараллеливать вычисление факториала для одного числа
 * <p>
 * 2) распараллелить вычисления для разных чисел
 * <p>
 * 3) комбинированный
 * <p>
 * При чем вычислив факториал для одного числа, можно запомнить эти данные и использовать их для вычисления другого, что будет гораздо быстрее .
 */
public class MultiFactorial {

    Map<Integer, BigInteger> knownFactorials;

    public Integer getClosestKnownFactorialBase(Integer arg) {
        return knownFactorials.keySet()
                .stream()
                .filter(k -> (k <= arg))
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();
    }

    //переделать метод в Callable
    public BigInteger computeFactorial(Integer base) {
        Integer closestKnownFactorialBase = getClosestKnownFactorialBase(base);//максимальное число < base, факториал которого известен
        List<Integer> numbers = IntStream.range(closestKnownFactorialBase, base + 1).boxed().collect(Collectors.toList());//числа, на которые нужно домножить base!
        //разделим список этих чисел на группы, чтобы многопоточно вычислить произведение каждой из групп
        List<List<Integer>> partition = partition(numbers, 100);
        List<Future<BigInteger>> semiResults = null;
        for(List list : partition){
            new NumbersMultiplier(list);
            //и кладём в пулл на исполнение
        }
        //соберём все промежуточные результаты и вернём
        BigInteger closestFactorial = knownFactorials.get(closestKnownFactorialBase);

        return null;

    }

    private List<List<Integer>> partition(List<Integer> numbers, int subCollectionSize) {
        List<List<Integer>> sublists = new ArrayList<>();
        //в Guava и Apache Commons Collections есть метод Lists.partition(bigList, sublistSize);
        for (int i = 0; i < numbers.size(); i+= subCollectionSize) {
            int n = i+subCollectionSize-1;
            if(n>= numbers.size()){
                sublists.add(numbers.subList(i, numbers.size()-1));
                break;
            }
            sublists.add(numbers.subList(i, n));
        }
        return sublists;
    }

    private BigInteger bigIntegerMultiplier(BigInteger factorial, List<BigInteger> semiResults){
        BigInteger result = factorial;
        semiResults.forEach(n -> new BigInteger(n.toString()).multiply(result));
        return result;
    }

    public MultiFactorial() {
        this.knownFactorials = new ConcurrentHashMap<>();
        knownFactorials.put(1, new BigInteger("1"));
    }
}
