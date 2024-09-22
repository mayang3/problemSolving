package leetcode;

/**
 * @author neo82
 */
public class MinimumNumberOfOperationsToSatisfyConditions {
    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1}, {0, 0, 0}};

        MinimumNumberOfOperationsToSatisfyConditions minimumNumberOfOperationsToSatisfyConditions = new MinimumNumberOfOperationsToSatisfyConditions();
        System.out.println(minimumNumberOfOperationsToSatisfyConditions.minimumOperations(grid));
    }

    public int minimumOperations(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;

        return solve(grid, -1, 0, M, N);
    }


    // time complexity O(10^N * M)
    private int solve(int[][] grid, int beforeNum, int i, int M, int N) {
        if (i >= N) {
            return 0;
        }

        int min = Integer.MAX_VALUE;

        for (int k = 0; k < 10; k++) {

            if (k != beforeNum) {
                int diff = 0;

                for (int j = 0; j < M; j++) {
                    diff += (grid[j][i] == k ? 0 : 1);
                }

                int res = solve(grid, k, i + 1, M, N);

                if (res != Integer.MAX_VALUE) {
                    min = Math.min(min, diff + res);
                }
            }
        }

        return min;
    }
}
