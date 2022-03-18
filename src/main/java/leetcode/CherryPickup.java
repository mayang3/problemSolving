package leetcode;

import java.util.ArrayList;
import java.util.List;

public class CherryPickup {
	public int cherryPickup(int[][] grid) {
		int res = 0;

		Pair pair = getMaximumPair(grid);
		res += pair.sum;

		for (int [] p : pair.pointList) {
			grid[p[0]][p[1]] = 0;
		}

		res += getMaximumPair(grid).sum;

		return res;
	}

	private Pair getMaximumPair(int[][] grid) {
		int n = grid.length;

		Pair [][] dp = new Pair[n][n];
		dp[0][0] = new Pair();
		dp[0][0].add(grid[0][0], 0, 0);

		// 맨 위
		for (int c = 1; c < n; c++) {
			if (grid[0][c] == -1 || dp[0][c-1].pointList.size() == 0) {
				dp[0][c] = new Pair();
			} else {
				dp[0][c] = new Pair(dp[0][c-1]);
				dp[0][c].add(grid[0][c], 0, c);
			}
		}

		// 맨 왼쪽
		for (int r = 1; r < n; r++) {
			if (grid[r][0] == -1 || dp[r-1][0].pointList.size() == 0) {
				dp[r][0] = new Pair();
			} else {
				dp[r][0] = new Pair(dp[r-1][0]);
				dp[r][0].add(grid[r][0], r, 0);
			}
		}

		// for DP
		for (int r = 1; r < n; r++) {
			for (int c = 1; c < n; c++) {
				if (grid[r][c] == -1 || (dp[r][c-1].pointList.size() == 0 && dp[r-1][c].pointList.size() == 0)) {
					dp[r][c] = new Pair();
				} else {
					// top
					if (dp[r-1][c].pointList.size() > 0) {
						dp[r][c] = new Pair(dp[r-1][c]);
						dp[r][c].add(grid[r][c], r, c);
					}

					// left
					if (dp[r][c-1].pointList.size() > 0) {
						if (dp[r][c] == null || (dp[r][c-1].sum + grid[r][c] > dp[r][c].sum)) {
							dp[r][c] = new Pair(dp[r][c-1]);
							dp[r][c].add(grid[r][c], r, c);
						}
					}
				}
			}
		}

		return dp[n-1][n-1];
	}

	static class Pair {
		int sum;
		List<int[]> pointList = new ArrayList<>();

		public Pair(Pair pair) {
			this.sum = pair.sum;
			this.pointList.addAll(pair.pointList);
		}

		public Pair() {
		}

		void add(int inc, int y, int x) {
			sum += inc;
			pointList.add(new int[] {y,x});
		}
	}

	public static void main(String[] args) {
		int [][] grid = {{1,1,1,1,0,0,0},{0,0,0,1,0,0,0},{0,0,0,1,0,0,1},{1,0,0,1,0,0,0},{0,0,0,1,0,0,0},{0,0,0,1,0,0,0},{0,0,0,1,1,1,1}};

		CherryPickup cherryPickup = new CherryPickup();
		System.out.println(cherryPickup.cherryPickup(grid));
	}
}
