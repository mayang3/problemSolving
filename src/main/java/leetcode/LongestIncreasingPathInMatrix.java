package leetcode;

public class LongestIncreasingPathInMatrix {
	static int [][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public int longestIncreasingPath(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;

		int max = Integer.MIN_VALUE;

		Integer [][] dp = new Integer[m][n];

		for (int y = 0; y < m; y++) {
			for (int x = 0; x < n; x++) {
				max = Math.max(max, solve(dp, matrix, y, x));
			}
		}

		return max;
	}

	private int solve(Integer [][] dp, int[][] matrix, int y, int x) {
		if (dp[y][x] != null) {
			return dp[y][x];
		}

		int max = 1;

		// next possible
		for (int [] dir : DIRECTIONS) {
			int nextY = y + dir[0];
			int nextX = x + dir[1];

			if (isPossible(matrix, nextY, nextX)) {
				if (matrix[y][x] >= matrix[nextY][nextX]) {
					max = Math.max(max, 1);
				} else {
					max = Math.max(max, 1 + solve(dp, matrix, nextY, nextX));
				}
			}
		}

		return dp[y][x] = max;
	}

	private boolean isPossible(int[][] matrix, int y, int x) {
		if (y < 0 || x < 0 || y >= matrix.length || x >= matrix[0].length) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		int [][] matrix = {{1}};

		LongestIncreasingPathInMatrix longestIncreasingPathInMatrix = new LongestIncreasingPathInMatrix();
		System.out.println(longestIncreasingPathInMatrix.longestIncreasingPath(matrix));
	}
}
