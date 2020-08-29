package sorting;

public class Selectionsort {

    public static void sort(int[] items) {
        for (int i = 0; i < items.length - 1; i++) {
            int lowest = i;
            for (int j = i + 1; j < items.length; j++) {
                if (items[j] < items[lowest])
                    lowest = j;
            }
            if (lowest != i)
                swap(items, i, lowest);
        }
    }

    private static void swap(int[] items, int a, int b) {
        int tmp = items[a];
        items[a] = items[b];
        items[b] = tmp;
    }
}
