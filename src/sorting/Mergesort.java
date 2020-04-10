package sorting;

public class Mergesort {

    public static int[] sort(int[] items) {
        if (items.length <= 1)
            return items;

        int[] left = new int[items.length / 2];
        int[] right = new int[items.length - items.length / 2];

        split(items, left, right);

        int[] _left = sort(left);
        int[] _right = sort(right);

        return collect(_left, _right);
    }

    private static void split(int[] source, int[] left, int[] right) {
        for (int i = 0; i < source.length / 2; i++)
            left[i] = source[i];

        for (int i = source.length / 2; i < source.length; i++)
            right[i - source.length / 2] = source[i];
    }

    private static int[] collect(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        int i = 0, j = 0, r = 0;
        while (i < first.length && j < second.length) {
            if (first[i] < second[j]) {
                result[r] = first[i];
                i++;
            } else {
                result[r] = second[j];
                j++;
            }
            r++;
        }
        if (i >= first.length) {
            for (int a = j; a < second.length; a++) {
                result[r] = second[a];
                r++;
            }
        } else if (j >= first.length) {
            for (int a = i; a < first.length; a++) {
                result[r] = first[a];
                r++;
            }
        }
        return result;
    }
}
