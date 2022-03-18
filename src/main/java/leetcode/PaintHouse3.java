package leetcode;

public class PaintHouse3 {
	public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
		Integer [][][] dp = new Integer[houses.length][n+1][m+1];
		return solve(dp, houses, cost, 0, n, target, n);
	}

	private int solve(Integer [][][] dp, int[] houses, int[][] cost, int i, int color, int target, int maxColor) {

		if (dp[i][color][target] != null) {
			return dp[i][color][target];
		}

		for (int j = 0; j < maxColor; j++) {

		}


		return 0;
	}
}
