package algorithms;

public class ProductOfArrayExceptSelf {

    /**
     * given an array values of length n. Return a new array output of length n where each
     * element at output[i] is the product of all values except values[i]
     * Note: do not use divsion and do it in O(n) time complexity
     * @param values
     * @return
     */
    public static int[] productOfArray(int[] values) {
        int[] left = new int[values.length];
        int[] right = new int[values.length];
        int[] output = new int[values.length];
        left[0] = 1;
        right[values.length - 1] = 1;
        for (int i = 1; i < values.length; i++) {
            left[i] = left[i-1] * values[i-1];
            right[values.length - i - 1] = values[values.length-i] * right[values.length - i];
        }
        for (int i = 0; i < values.length; i++) {
            output[i] = left[i] * right[i];
        }
        return output;
    }

    public static int[] productOfArrayWithDivision(int[] values) {
        int product = 1;
        int[] output = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            product *= values[i];
        }
        for (int i = 0; i < output.length; i++) {
            output[i] = product / values[i];
        }
        return output;
    }
}
