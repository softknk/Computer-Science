package sorting;

public class Insertionsort {

    public static void sort(int[] items) {
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < i; j++) {
                if (items[i] < items[j]) {
                    int tmp = items[i];
                    clearArrayAtIndex(items, i, j);
                    items[j] = tmp;
                }
            }
        }
    }

    private static void clearArrayAtIndex(int[] items, int remove, int gap) {
        for (int i = remove; i < items.length - 1; i++) {
            items[i] = items[i+1];
        }
        for (int i = items.length - 1; i > gap; i--) {
            items[i] = items[i-1];
        }
    }

}
