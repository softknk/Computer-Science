package algorithms;

public class SodokuSolver {

    static int[][] sodoku = {
            {9, 0, 0, 1, 0, 0, 0, 0, 5},
            {0, 0, 5, 0, 9, 0, 2, 0, 1},
            {8, 0, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 0, 0, 8, 0, 0, 0, 0},
            {0, 0, 0, 7, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 2, 6, 0, 0, 9},
            {2, 0, 0, 3, 0, 0, 0, 0, 6},
            {0, 0, 0, 2, 0, 0, 9, 0, 0},
            {0, 0, 1, 9, 0, 4, 5, 7, 0},
    };

    public static void main(String[] args) {
        if (solve(sodoku)) {
            for (int i = 0; i < sodoku.length; i++) {
                for (int j = 0; j < sodoku.length; j++)
                    System.out.print(sodoku[i][j] + " ");
                System.out.println(" ");
            }
        }
    }

    public static boolean solve(int[][] sodoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sodoku[i][j] == 0) {
                    for (int a = 1; a < 10; a++) {
                        if (isPossible(sodoku, i, j, a)) {
                            sodoku[i][j] = a;
                            if (solve(sodoku))
                                return true;
                            sodoku[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isPossible(int[][] sodoku, int row, int column, int value) {
        boolean isPossible = true;

        for (int i = 0; i < 9; i++) {
            if (sodoku[row][i] == value || sodoku[i][column] == value)
                isPossible = false;
        }

        int row_field = (row / 3) * 3;
        int column_field = (column / 3) * 3;

        for (int i = row_field; i < row_field + 3; i++) {
            for (int j = column_field; j < column_field + 3; j++) {
                if (sodoku[i][j] == value)
                    isPossible = false;
            }
        }

        return isPossible;
    }
}
