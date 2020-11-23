package algorithms;

/**
 * solves the problem of finding the highest number
 * with the help of QuickSelect algorithm
 */
public class HighestNumber {

    public static void main(String[] args) {
        int[] values = {2, 3, 1, 6, 4, 8, 3, 2};
        System.out.println(getHighestNumber(values));
    }

    public static int getHighestNumber(int[] values) {
        while (true) {
            if (partition(values) == values.length - 1)
                return values[values.length - 1];
        }
    }

    private static int partition(int[] values) {
        int pivotIndex = values.length - 1;
        int pivot = values[pivotIndex];
        int i = 0, j = values.length - 1;

        while (i < j) {
            while (i < values.length - 1 && values[i] < pivot)
                i++;
            while (j > 0 && values[j] >= pivot)
                j--;

            if (i < j)
                swap(values, i, j);
        }

        if (values[i] > pivot)
            swap(values, i, pivotIndex);

        return i;
    }

    private static void swap(int[] values, int i, int j) {
        int tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }
}
