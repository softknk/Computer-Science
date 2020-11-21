package algorithms;

import java.util.Stack;

public class TowerOfHanoi {

    public static void hanoi(int n, Stack<Integer> from, Stack<Integer> target, Stack<Integer> help) {
        if (n == 0)
            return;

        hanoi(n-1, from, help, target);
        target.push(from.pop());
        hanoi(n-1, help, target, from);
    }

    public static void main(String[] args) {

        int number = 5;

        Stack<Integer> from = new Stack<>();
        Stack<Integer> target = new Stack<>();
        Stack<Integer> help = new Stack<>();

        for (int i = number; i > 0; i--)
            from.push(i);

        hanoi(5,from, target, help);

        // debugging purpose
        while (!target.isEmpty())
            System.out.print(target.pop() + " ");
    }
}
