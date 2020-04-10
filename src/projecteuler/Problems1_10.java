package projecteuler;

import java.util.ArrayList;
import java.util.List;

public class Problems1_10 {

    //1
    public static int multiplesOf3or5(int range) {
        int sum = 0;
        for (int i = 2; i < range; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        return sum;
    }

    //2
    public static int evenFibonacciNumbers(int range) {
        int sum = 0;
        for (int i = 1; i < range; i++) {
            int fib = fib(i);
            if ((fib & 1) == 0)
                sum += fib;
        }
        return sum;
    }

    private static int fib(int a) {
        if (a == 1 || a == 0)
            return 1;
        else if (a == 2)
            return 2;
        else
            return fib(a - 1) + fib(a - 2);
    }

    //3
    public static List<Integer> primeFactorsOf(int value) {
        List<Integer> primes = new ArrayList<>();
        while (value != 1) {
            for (int i = 2; i <= value; i++) {
                if (isPrime(i) && value % i == 0) {
                    primes.add(i);
                    value /= i;
                    break;
                }
            }
        }
        return primes;
    }

    private static boolean isPrime(int a) {
        if (a <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(a); i++) {
            if (a % i == 0)
                return false;
        }
        return true;
    }
}

class Testing {

    public static void main(String[] args) {
        System.out.println(Problems1_10.multiplesOf3or5(1000));
        System.out.println(Problems1_10.evenFibonacciNumbers(10));
        List<Integer> list = Problems1_10.primeFactorsOf(14);
        for (int i : list)
            System.out.print(i + " ");
    }
}