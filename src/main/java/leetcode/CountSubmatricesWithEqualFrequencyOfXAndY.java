package leetcode;

/**
 * @author neo82
 */
public class CountSubmatricesWithEqualFrequencyOfXAndY {
    public int numberOfSubmatrices(char[][] grid) {
        int M = grid.length;
        int N = grid[0].length;

        int[][] prefixMatrix = new int[M][N];
        int[][] sumMatrix = new int[M][N];
        boolean[][] containsMatrix = new boolean[M][N];
        boolean[][] sumContainsMatrix = new boolean[M][N];

        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {
                prefixMatrix[row][col] = prefixMatrix[row][Math.max(col - 1, 0)] + getGridValue(grid, row, col);
                containsMatrix[row][col] = containsMatrix[row][Math.max(col - 1, 0)] || getGridValue(grid, row, col) == 1;
                sumMatrix[row][col] = prefixMatrix[row][col] + sumMatrix[Math.max(row - 1, 0)][col];
                sumContainsMatrix[row][col] = containsMatrix[row][col] || sumContainsMatrix[Math.max(row - 1, 0)][col];
            }
        }

        int ans = 0;

        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {

                if (row == 0 && prefixMatrix[row][col] == 0 && containsMatrix[row][col]) {
                    ans++;
                }

                if (row > 0 && (prefixMatrix[row][col] + sumMatrix[row - 1][col] == 0) && (containsMatrix[row][col] || sumContainsMatrix[row - 1][col])) {
                    ans++;
                }

            }
        }

        return ans;
    }

    int getGridValue(char[][] grid, int row, int col) {
        if (grid[row][col] == 'X') {
            return 1;
        } else if (grid[row][col] == 'Y') {
            return -1;
        }

        return 0;
    }

    // . X .
    // X . X
    // Y Y .
    // . X X

    // X X
    // X Y

    public static void main(String[] args) {
        char[][] grid = {{'.', 'X', '.'}, {'X', '.', 'X'}, {'Y', 'Y', '.'}, {'.', 'X', 'X'}};

        CountSubmatricesWithEqualFrequencyOfXAndY countSubmatricesWithEqualFrequencyOfXAndY = new CountSubmatricesWithEqualFrequencyOfXAndY();
        System.out.println(countSubmatricesWithEqualFrequencyOfXAndY.numberOfSubmatrices(grid));
    }


}
