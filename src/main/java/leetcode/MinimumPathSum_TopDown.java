package leetcode;

/**
 * @author neo82
 */
public class MinimumPathSum_TopDown {
	public int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		int m = grid.length;
		int n = grid[0].length;

		Integer [][] dp = new Integer[m][n];

		return solve(dp, grid, 0, 0, m, n);
	}

	private int solve(Integer[][] dp, int[][] grid, int y, int x, int m, int n) {
		if (dp[y][x] != null) {
			return dp[y][x];
		}

		int sum = grid[y][x];
		int min = Integer.MAX_VALUE;

		// down
		if (isValid(grid, y+1, x, m, n)) {
			min = Math.min(min, solve(dp, grid, y + 1, x, m, n));
		}

		// right
		if (isValid(grid, y, x+1, m, n)) {
			min = Math.min(min, solve(dp, grid, y, x + 1, m, n));
		}

		if (min != Integer.MAX_VALUE) {
			sum += min;
		}

		return dp[y][x] = sum;
	}

	private boolean isValid(int[][] grid, int y, int x, int m, int n) {
		if (y < 0 || y >= m || x < 0 || x >= n) {
			return false;
		}

		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] grid = {
			{1, 3, 1},
			{1, 5, 1},
			{4, 2, 1}
		};

		MinimumPathSum_TopDown mps = new MinimumPathSum_TopDown();

		System.out.println(mps.minPathSum(grid));
	}
}
