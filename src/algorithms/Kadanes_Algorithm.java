package algorithms;

public class Kadanes_Algorithm {

    public static void main(String[] args) {
        int[] a = {1, -2, 3, 2, -1};
        System.out.println(sumMaxSubArray(a));
    }

    /**
     * returns the sum of the maximum sub array possible
     * e.x. [1, 2, 4, 7, -2, 3] -> 11 -> [4, 7]
     *
     * time: O(n)
     * space: O(1)
     *
     * @param a
     * @return
     */

    public static int sumMaxSubArray(int[] a) {
        int max_current, max_global;
        max_current = max_global = a[0];

        for (int i = 1; i < a.length; i++) {
            max_current = Integer.max(a[i], max_current + a[i]);
            max_global = (max_current > max_global) ? max_current : max_global;
        }

        return max_global;
    }
}