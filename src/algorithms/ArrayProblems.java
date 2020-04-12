package algorithms;

public class ArrayProblems {

    public static void main(String[] args) {
        System.out.println(findDuplicate(new int[]{7, 6, 3, 5, 6, 1, 2, 4}));
        System.out.println(_findDuplicate(new int[]{7, 6, 3, 5, 4, 1, 2, 4}));
    }

    /**
     * assume that the values of array n are always between 0 and n-1
     * @param n
     * @return
     */
    public static int findDuplicate(int[] n) {
        for (int i = 0; i < n.length; i++) {
            if (n[Math.abs(n[i])] >= 0) {
                n[Math.abs(n[i])] = -n[Math.abs(n[i])];
            } else {
                return Math.abs(n[i]);
            }
        }
        return -1;
    }

    /**
     * Find duplicates in constant array with elements 0 to N-1 in O(1) space
     * --> Floyd's Tortoise and Hare algorithm
     * @param n
     * @return
     */
    public static int _findDuplicate(int[] n) {
        int slow = n[0], fast = n[n[0]];
        while (slow != fast) {
            slow = n[slow];
            fast = n[n[fast]];
        }
        //find entry point of the cycle
        fast = 0;
        while (slow != fast) {
            slow = n[slow];
            fast = n[fast];
        }
        return slow;
    }
}
