package algorithms;

import java.util.HashMap;

public class SimpleRecursion {

    public static int fak(int n) {
        if (n == 0)
            return 1;
        else
            return n * fak(n-1);
    }

    public static int fib(int n) {
        if (n == 0 || n == 1)
            return 1;
        else
            return fib(n-1) + fib(n-2);
    }

    public static int fib_dynamic(int n, HashMap<Integer, Integer> map) {
        if (n == 0 || n == 1)
            return 1;
        else if (map.containsKey(n))
            return map.get(n);
        else {
            int result = fib(n-1) + fib(n-2);
            map.put(n, result);
            return result;
        }
    }
}
