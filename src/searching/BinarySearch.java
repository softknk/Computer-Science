package searching;

public class BinarySearch {

    //works only for sorted arrays
    public static int binarySearch(int[] items, int value) {
        int start = 0, end = items.length - 1;
        int pointer;
        while (end - start > 0) {
            pointer = start + (end - start) / 2;
            if (items[pointer] == value) {
                return pointer;
            } else if (items[pointer] > value) {
                end = pointer - 1;
            } else {
                start = pointer + 1;
            }
        }
        if (items[start] == value)
            return start;
        else
            return -1;
    }

    public static int binarySearchRecursive(int[] items, int value, int start, int end) {
        if (end - start < 1)
            return (items[start] == value) ? start : -1;

        int pointer = start + (end - start) / 2;

        if (items[pointer] == value)
            return pointer;
        else if (items[pointer] > value)
            return binarySearchRecursive(items, value, start, pointer - 1);
        else
            return binarySearchRecursive(items, value, pointer + 1, end);
    }

    public static void main(String[] args) {
        int[] items = {2, 5, 7, 9, 12, 45, 98};
        System.out.println(binarySearch(items, 98));
        System.out.println(binarySearchRecursive(items, 104, 0, items.length - 1));
    }
}
