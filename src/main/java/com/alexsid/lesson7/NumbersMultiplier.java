package com.alexsid.lesson7;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.Callable;

//класс для перемножения части значений при вычислении одного факториала
public class NumbersMultiplier implements Callable<BigInteger> {
    private List<Integer> numbers;

    public NumbersMultiplier(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public BigInteger call() throws Exception {
        BigInteger result = new BigInteger("1");
        for (Integer i: numbers) {
            result = result.multiply(new BigInteger(String.valueOf(i)));
        }
        return result;
    }
}
