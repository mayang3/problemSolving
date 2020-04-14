package leetcode;

public class MaximalSquare {
	public int maximalSquare(char[][] matrix) {

		if (matrix == null || matrix.length == 0) {
			return 0;
		}

		int n = matrix.length;
		int m = matrix[0].length;

		int len = 0;

		int [][] dp = new int[n][m];

		for (int i = 0; i < n; i++) {
			dp[i][0] = Character.getNumericValue(matrix[i][0]);

			if (dp[i][0] == 1) {
				len = 1;
			}
		}

		for (int i = 0; i < m; i++) {
			dp[0][i] = Character.getNumericValue(matrix[0][i]);
			if (dp[0][i] == 1) {
				len = 1;
			}
		}

		for (int y = 1; y < n; y++) {
			for (int x = 1; x < m; x++) {
				if (matrix[y][x] == '1') {
					// 바로 위, 바로 옆, 대각선 위에 있는 값 + 1의 값이 현재 사각형의 length 가 된다.
					dp[y][x] = Math.min(Math.min(dp[y-1][x-1], dp[y][x-1]), dp[y-1][x]) + 1;
					len = Math.max(len, dp[y][x]);
				}
			}
		}

		return len * len;
	}

	public static void main(String[] args) {
		char [][] mat = {
			{'1'}
		};

		MaximalSquare maximalSquare = new MaximalSquare();
		int res = maximalSquare.maximalSquare(mat);

		System.out.println(res);
	}
}
