package leetcode;

public class UniquePaths {
	public int uniquePaths(int m, int n) {
		int [][] matrix = new int[m][n];

		matrix[0][0] = 0;

		for (int x = 0; x < n; x++) {
			matrix[0][x] = 1;
 		}

		for (int y = 0; y < m; y++) {
			matrix[y][0] = 1;
		}

		for (int y = 1; y < m; y++) {
			for (int x = 1; x < n; x++) {
				matrix[y][x] = matrix[y-1][x] + matrix[y][x-1];
			}
		}

		return matrix[m-1][n-1];
	}

	public static void main(String[] args) {
		int m = 3;
		int n = 2;

		UniquePaths up = new UniquePaths();
		int res = up.uniquePaths(m, n);

		System.out.println(res);
	}
}
