package com.alexsid.lesson3.numbergenerator;

import java.util.Scanner;

/**2. Составить программу, генерирующую N случайных чисел.
 * Для каждого числа k вычислить квадратный корень q.
 * Если квадрат целой части q числа равен k, то вывести это число на экран.
 * Предусмотреть что первоначальные числа могут быть отрицательные, в этом случае генерировать исключение.
 */
 public class NumberGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число N");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            try {
                double k = Math.random() * 20 - 5;//от -5 до 15
                if(k < 0) {
                    throw new NumberIsLowerThanZeroException(k);
                }
                int q = (int)Math.sqrt(k);
                if (q*q == (int)k){
                    System.out.println(k);
                }
            } catch (NumberIsLowerThanZeroException e) {
                System.out.format("Число оказалось отрицательным: %f%n",e.getReason());
            }
        }
    }

    private static class NumberIsLowerThanZeroException extends RuntimeException {

        public double getReason() {
            return reason;
        }

        private final double reason;

        public NumberIsLowerThanZeroException(double random) {
            this.reason = random;
        }
    }
}
