package algorithms;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayProblems {

    public static void main(String[] args) {
        System.out.println(findDuplicate(new int[]{7, 6, 3, 5, 6, 1, 2, 4}));
        System.out.println(_findDuplicate(new int[]{7, 6, 3, 5, 4, 1, 2, 4}));

        List<Integer> a = Arrays.asList(1, 9, 2, 8, 7, 3, 6, 4, 5);
        partition(5, a);
        for (Integer _a : a)
            System.out.print(a + " ");
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

    /**
     * move every item in the list which is less than the pivot to the left
     * and every item greater than the pivot to the right
     *
     * time: O(n)
     * space: O(1)
     *
     * @param pivot
     * @param list
     */
    public static void partition(int pivot, List<Integer> list) {
        int smaller = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < pivot)
                Collections.swap(list, smaller++, i);
        }

        int larger = list.size() - 1;
        for (int i = list.size() - 1; i >= 0 && list.get(i) >= pivot; i--) {
            if (list.get(i) > pivot)
                Collections.swap(list, larger--, i);
        }
    }
}