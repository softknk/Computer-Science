package algorithms;

public class QueensProblem {

    static int[] queens = new int[8];

    static {
        for (int i = 0; i < queens.length; i++)
            queens[i] = Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        solve(0);
        for (int a : queens)
            System.out.println(a);
    }

    public static boolean solve(int row) {
        if (row >= 8)
            return true;

        for (int i = 0; i < 8; i++) {
            if (canPlace(row, i)) {
                queens[row] = i;
                if (solve(row + 1))
                    return true;
                queens[row] = Integer.MIN_VALUE;
            }
        }
        return false;
    }

    private static boolean canPlace(int row, int column) {
        boolean canPlace = true;

        for (int i = 0; i < queens.length; i++) {
            if (queens[i] == column || queens[i] + i == column + row || queens[i] - i == column - row)
                canPlace = false;
        }

        return canPlace;
    }
}
