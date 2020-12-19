package searching;

public class BinarySearch {

    //works only for sorted arrays
    public static int binarySearch(int[] items, int value) {
        int start = 0, end = items.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (items[mid] == value) return mid;
            else if (items[mid] > value) end = mid - 1;
            else start = mid + 1;
        }
        return (items[start] == value) ? start : -1;
    }

    public static int binarySearchRecursive(int[] items, int value, int start, int end) {
        if (start >= end)
            return (items[start] == value) ? start : -1;

        int mid = (start + end) / 2;

        if (items[mid] == value) return mid;
        else if (items[mid] > value) return binarySearchRecursive(items, value, start, mid - 1);
        else return binarySearchRecursive(items, value, mid + 1, end);
    }

    public static void main(String[] args) {
        int[] items = {2, 5, 7, 9, 12, 45, 98};
        System.out.println(binarySearch(items, 98));
        System.out.println(binarySearchRecursive(items, 104, 0, items.length - 1));
    }
}
