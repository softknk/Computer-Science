package different;

public class QuickSelect {

    /**
     * the function should return the nth lowest number in the given array
     * @param n
     * @param m
     * @return
     */

    public static int min(int[] n, int m) {
        int partiton = partition(n, m - 1);

        if (partiton == m - 1)
            return n[m - 1];
        else
            return min(n, m);
    }

    private static int partition(int[] n, int m) {
        int i = 0, j = n.length - 1;
        int pivot = n[m];
        while (i < j) {
            while (i < n.length - 1 && n[i] < pivot)
                i++;
            while (j > 0 && n[j] >= pivot)
                j--;

            if (i < j) {
                int tmp = n[i];
                n[i] = n[j];
                n[j] = tmp;
            }
        }

        if (n[i] > pivot) {
            int tmp = n[i];
            n[i] = n[m];
            n[m] = tmp;
        }
        return i;
    }
}
