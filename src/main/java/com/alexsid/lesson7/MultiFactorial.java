package com.alexsid.lesson7;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;
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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MultiFactorial multiFactorial = new MultiFactorial();
        multiFactorial.computeFactorials(new Integer[]{27300, 27423, 17200, 1270});
        //послдняя нерешённая проблема
        //UPD: Закомментил - не упало...
//        multiFactorial.service.shutdown();//если переношу в computeFactorials - слишком рано глушит пул и всё падает
    }

    private Map<Integer, BigInteger> knownFactorials;
    private final ExecutorService service = Executors.newWorkStealingPool();

    public void computeFactorials(Integer[] bases) throws ExecutionException, InterruptedException {
        long start = System.nanoTime();
        for (Integer base : bases) {
            Callable<BigInteger> task = () -> computeFactorial(base);
            Future<BigInteger> compResult = service.submit(task);
            System.out.println(compResult.get());
        }
        System.out.println(System.nanoTime() - start);
    }

    private Integer getClosestKnownFactorialBase(Integer arg) {
        return knownFactorials.keySet()
                .stream()
                .filter(k -> (k <= arg))
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();
    }

    public BigInteger computeFactorial(Integer base) throws ExecutionException, InterruptedException {
        Integer closestKnownFactorialBase = getClosestKnownFactorialBase(base);//максимальное число < base, факториал которого известен
        List<Integer> numbers =
                IntStream
                        .rangeClosed(closestKnownFactorialBase, base)
                        .boxed()
                        .collect(Collectors.toList());//числа, на которые нужно домножить base factorial

        //разделим список этих чисел на группы, чтобы многопоточно вычислить произведение каждой из групп
        List<List<Integer>> partition = partition(numbers, 100);//hardcode

        List<BigInteger> bigInts = new ArrayList<>();
        for (List list : partition) {
            NumbersMultiplier numbersMultiplier = new NumbersMultiplier(list);
            Future<BigInteger> semiResult = service.submit(numbersMultiplier);
            bigInts.add(semiResult.get());
        }
        //соберём все промежуточные результаты в один, закэшируем его и вернём
        BigInteger closestFactorial = knownFactorials.get(closestKnownFactorialBase);
        BigInteger result = bigIntegerMultiplier(closestFactorial, bigInts);
        knownFactorials.put(base, result);
        return result;
    }

    //в Guava и Apache Commons Collections есть метод Lists.partition(bigList, sublistSize);
    //раньше я бы вынес его статическим методом в утилитарный класс
    public List<List<Integer>> partition(List<Integer> numbers, int subCollectionSize) {
        List<List<Integer>> sublists = new ArrayList<>();
        for (int i = 0; i <= numbers.size(); i += subCollectionSize) {
            int n = i + subCollectionSize;
            if (n >= numbers.size()) {
                sublists.add(numbers.subList(i, numbers.size()));
                break;
            }
            sublists.add(numbers.subList(i, n));
        }
        return sublists;
    }

    //раньше я бы вынес его статическим методом в утилитарный класс
    private BigInteger bigIntegerMultiplier(BigInteger factorial, List<BigInteger> semiResults) {
        BigInteger result = factorial;
        for (BigInteger bi : semiResults) {
            result = result.multiply(bi);
        }
        return result;
    }

    public MultiFactorial() {
        this.knownFactorials = new ConcurrentHashMap<>();
        knownFactorials.put(1, new BigInteger("1"));
    }

    //а как в тестах, вынесенных в отдельную ветку проекта, получать доступ к privat полям и методам?
    public Map<Integer, BigInteger> getKnownFactorials() {
        return knownFactorials;
    }
}
