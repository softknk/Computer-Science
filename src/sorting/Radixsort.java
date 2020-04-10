package sorting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Radixsort {

    static List<Integer>[] buckets = new List[10];

    static {
        for (int i = 0; i < buckets.length; i++)
            buckets[i] = new ArrayList<>();
    }

    public static void sort(int[] items) {
        int maxDigits = maxDigits(items);
        for (int i = 0; i < maxDigits; i++) {
            for (int j = 0; j < items.length; j++) {
                int digit = getDigit(items[j], i);
                buckets[digit].add(items[j]);
            }
            collect(items);
            clearBuckets();
        }
    }

    private static int getDigit(int value, int digit) {
        return (value / (int) (Math.pow(10, digit))) % 10;
    }

    private static void collect(int[] items) {
        int current = 0;
        for (int i = 0; i < buckets.length; i++) {
            Iterator<Integer> iterator = buckets[i].iterator();
            while (iterator.hasNext()) {
                items[current] = iterator.next();
                current++;
            }
        }
    }

    private static void clearBuckets() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i].clear();
        }
    }

    private static int maxDigits(int[] items) {
        int max = 0;
        for (int i = 0; i < items.length; i++) {
            int digits = 0;
            int tmp = items[i];
            while (tmp >= 1) {
                tmp /= 10;
                digits++;
            }
            if (digits > max)
                max = digits;
        }
        return max;
    }
}
