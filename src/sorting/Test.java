package sorting;

import static sorting.Array.print;

public class Test {

    static int[] items = {23, 12, 2, 5, 6, 8, 9, 3, 45, 11, 8};
    static int[] items2 = {123, 467, 234, 9786, 367, 987, 674};

    public static void main(String[] args) {
       // testBubblesort();
        testSelectionSort();
       // testInsertionsort();
       // testQuicksort();
       // testRadixsort();
       // testMergesort();
    }

    private static void testBubblesort() {
        Bubblesort.sort(items);
        print(items);
    }

    private static void testSelectionSort() {
        Selectionsort.sort(items);
        print(items);
    }

    private static void testInsertionsort() {
        Insertionsort.sort(items);
        print(items);
    }

    private static void testQuicksort() {
        Quicksort.sort(items, 0, items.length - 1);
        print(items);
    }

    private static void testRadixsort() {
        Radixsort.sort(items2);
        print(items2);
    }

    private static void testMergesort() {
        int[] _items = Mergesort.sort(items2);
        print(_items);
    }
}
