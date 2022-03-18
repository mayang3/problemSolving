package leetcode;

public class KnightProbabilityInChessBoard {
	static int [][] DIRECTIONS = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

	public double knightProbability(int n, int k, int row, int column) {
		Double [][][] dp = new Double[n][n][k+1];

		return solve(dp, n,k,row,column);
	}

	private double solve(Double [][][] dp, int n, int k, int row, int column) {
		if (k == 0) {
			return 1;
		}

		double ret = 0;

		if (dp[row][column][k] != null) {
			return dp[row][column][k];
		}

		for (int [] dir : DIRECTIONS) {
			int nextRow = row + dir[0];
			int nextCol = column + dir[1];

			if (isPossible(n, nextRow, nextCol)) {
				ret += 0.125 * solve(dp, n, k - 1, nextRow, nextCol);
			}
		}

		return dp[row][column][k] = ret;
	}

	private boolean isPossible(int n, int nextRow, int nextCol) {
		if (nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		KnightProbabilityInChessBoard knightProbabilityInChessBoard = new KnightProbabilityInChessBoard();
		System.out.println(knightProbabilityInChessBoard.knightProbability(8,30,6,4));
	}
}
