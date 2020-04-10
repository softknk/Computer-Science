package sorting;

public class Bubblesort {

    public static void sort(int[] items) {
        for (int i = 1; i < items.length; i++) {
            for (int j = 0; j < items.length - i; j++) {
                if (items[j] > items[j+1])
                    swap(items, j,j+1);
            }
        }
    }

    private static void swap(int[] items, int i, int j) {
        int tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }
}
