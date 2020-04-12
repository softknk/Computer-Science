package algorithms;

public class AddOneProblem {

    /**
     * assuming that our function takes an array n where every single integer
     * represents a digit of a number e.x [1, 2, 4, 5] -> 1245
     * the function should add one to this number and should return the number in
     * the same way as it was given (as an array)
     * @param n
     * @return
     */

    public static int[] add_one(int[] n) {
        int[] result = n.clone();
        if (n[n.length-1] < 9) {
            result[n.length-1] = result[n.length-1] + 1;
            return result;
        } else {
            int i = result.length - 1;
            while (i >= 0 && result[i] == 9) {
                result[i] = 0;
                i--;
            }
            if (i < 0) {
                //if array contains only nines
                int[] _result = new int[result.length + 1];
                _result[0] = 1;
                for (int a = 1; a < result.length; a++) {
                    _result[a] = result[a];
                }
                return _result;
            } else {
                result[i] = result[i] + 1;
                return result;
            }
        }
    }
}
