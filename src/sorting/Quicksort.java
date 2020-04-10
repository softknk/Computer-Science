package sorting;

public class Quicksort {

    public static void sort(int[] items, int start, int end) {
        int partition = partition(items, start, end);

        if (end - start >= 1) {
            sort(items, start, partition - 1);
            sort(items, partition + 1, end);
        }
    }

    private static int partition(int[] items, int start, int end) {
        int pivotIndex = start + (end - start);
        int i = start, j = end;
        while (i < j) {
            while (items[i] < items[pivotIndex])
                i++;
            while (items[j] > items[pivotIndex])
                j--;
            swap(items, i, j);
        }
        return i;
    }

    private static void swap(int[] items, int i, int j) {
        int tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }
}
