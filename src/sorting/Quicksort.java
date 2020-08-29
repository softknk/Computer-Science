package sorting;

public class Quicksort {

    public static void sort(int[] items, int start, int end) {
        int partition = partition(items, start, end);

        if (end > start) {
            sort(items, start, partition - 1);
            sort(items, partition + 1, end);
        }
    }

    private static int partition(int[] items, int start, int end) {
        if (start >= end)
            return 0;

        int pivotIndex = end;
        int pivot = items[pivotIndex];
        int i = start, j = end;

        while (i < j) {
            while (i < end && items[i] < pivot)
                i++;
            while (j > start && items[j] >= pivot)
                j--;

            if (i < j)
                swap(items, i, j);
        }

        if (items[i] > pivot)
            swap(items, i, pivotIndex);

        return i;
    }

    private static void swap(int[] items, int i, int j) {
        int tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }
}
