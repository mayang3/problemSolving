package leetcode;

/**
 * @author neo82
 */
public class MinimumPathSum_BottomUp {
	public int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		int m = grid.length;
		int n = grid[0].length;

		int [][] dp = new int[m][n];

		dp[0][0] = grid[0][0];

		for (int y = 1; y < m; y++) {
			dp[y][0] = dp[y-1][0] + grid[y][0];
		}

		for (int x = 1; x < n; x++) {
			dp[0][x] = dp[0][x-1] + grid[0][x];
		}

		for (int y = 1; y < m; y++) {
			for (int x = 1; x < n; x++) {
				dp[y][x] = grid[y][x] + Math.min(dp[y][x-1], dp[y-1][x]);
			}
		}

		return dp[m-1][n-1];
	}

	public static void main(String[] args) {
		int[][] grid = {
			{1, 3, 1},
			{1, 5, 1},
			{4, 2, 1}
		};

		MinimumPathSum_BottomUp mps = new MinimumPathSum_BottomUp();

		System.out.println(mps.minPathSum(grid));
	}
}
