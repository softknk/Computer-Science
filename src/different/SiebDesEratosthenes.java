package different;

import java.util.ArrayList;
import java.util.List;

public class SiebDesEratosthenes {

    public static void main(String[] args) {
        List<Integer> primes = primeNumbers(100);
        for (int a : primes)
            System.out.println(a);
    }

    public static List<Integer> primeNumbers(int border) {
        State[] states = new State[border];
        List<Integer> primes = new ArrayList<>();
        primes.add(1);
        for (int i = 0; i < states.length; i++)
            states[i] = State.UNMARKED;
        for (int i = 2; i < states.length; i++) {
            if (states[i] == State.UNMARKED) {
                primes.add(i);
                for (int j = i; j < states.length; j++) {
                    if (j % i == 0)
                        states[j] = State.MARKED;
                }
            }
        }
        return primes;
    }

    private enum State {
        UNMARKED, MARKED
    }
}
