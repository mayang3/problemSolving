package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridGame {

	public long gridGame(int[][] grid) {
		int n = grid[0].length;

		PathAndPoint robot1Res = solve(grid, n);

		for (int [] path : robot1Res.paths) {
			grid[path[0]][path[1]] = 0;
		}

		PathAndPoint robot2Res = solve(grid, n);

		return robot2Res.point;
	}

	private PathAndPoint solve(int[][] grid, int n) {
		PathAndPoint [][] dp = new PathAndPoint[2][n];

		dp[0][0] = new PathAndPoint();
		dp[0][0].paths = Arrays.asList(new int[]{0,0});
		dp[0][0].point = grid[0][0];

		// row
		dp[1][0] = new PathAndPoint();
		dp[1][0].add(dp[0][0], 1, 0, grid[1][0]);

		// col
		for (int c = 1; c < n; c++) {
			dp[0][c] = new PathAndPoint();
			dp[0][c].add(dp[0][c-1], 0, c, grid[0][c]);
		}

		for (int c = 1; c < n; c++) {
			dp[1][c] = new PathAndPoint();

			if (dp[0][c].point > dp[1][c-1].point) {
				dp[1][c].add(dp[0][c], 1, c, grid[1][c]);
			} else {
				dp[1][c].add(dp[1][c-1], 1, c, grid[1][c]);
			}
		}

		return dp[1][n-1];
	}

	static class PathAndPoint {
		List<int []> paths = new ArrayList<>();
		long point;

		void add(PathAndPoint before, int r, int c, long point) {
			this.paths = new ArrayList<>();
			this.paths.addAll(before.paths);
			this.paths.add(new int[] {r, c});
			this.point = point + before.point;
		}
	}

	public static void main(String[] args) {
		int [][] grid = {{20,3,20,17,2,12,15,17,4,15},{20,10,13,14,15,5,2,3,14,3}};

		// [[20,3,20,17,2,12,15,17,4,15],[20,10,13,14,15,5,2,3,14,3]]

		GridGame gridGame = new GridGame();
		System.out.println(gridGame.gridGame(grid));
	}
}
