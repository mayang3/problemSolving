package leetcode;

public class PaintHouse {
	public int minCost(int[][] costs) {

		Integer [][] dp = new Integer[costs.length][4];

		return solve(dp, costs, 0, 3);
	}

	private int solve(Integer[][] dp, int[][] costs, int house, int forbiddenColor) {
		if (house >= costs.length) {
			return 0;
		}

		if (dp[house][forbiddenColor] != null) {
			return dp[house][forbiddenColor];
		}

		int min = Integer.MAX_VALUE;

		for (int color = 0; color < 3; color++) {
			if (color != forbiddenColor) {
				min = Math.min(min, costs[house][color] + solve(dp, costs, house+1, color));
			}
		}

		return dp[house][forbiddenColor] = min;
	}

	public static void main(String[] args) {
		int [][] costs = {{7,6,2}};

		PaintHouse paintHouse = new PaintHouse();
		System.out.println(paintHouse.minCost(costs));
	}
}
