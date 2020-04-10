package searching;

public class ArraySearch {

    //returns either the index of the given value in the array or -1
    public static <T> int searchValue(T[] array, T value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value))
                return i;
        }
        return -1;
    }
}
