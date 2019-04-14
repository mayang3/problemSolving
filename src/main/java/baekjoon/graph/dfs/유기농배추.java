package baekjoon.graph.dfs;

import java.util.Scanner;

public class 유기농배추 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int M = scanner.nextInt();
			int N = scanner.nextInt();
			int K = scanner.nextInt();

			int [][] matrix = new int[N][M];

			for (int i = 0; i < K; i++) {
				int x = scanner.nextInt();
				int y = scanner.nextInt();

				matrix[y][x] = 1;

			}

			System.out.println(solve(matrix, M, N));
		}
	}

	private static int solve(int[][] matrix, int m, int n) {

		int count = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] == 1) {
					count++;
					dfs(i, j, matrix, n, m);
				}
			}
		}

		return count;
	}

	static void dfs(int y, int x, int[][] matrix, int n, int m) {
		matrix[y][x] = 0;

		// top
		if (isValid(y+1, x, matrix, n, m)) {
			dfs(y+1, x, matrix, n, m);
		}

		// bottom
		if (isValid(y-1, x, matrix, n, m)) {
			dfs(y-1, x, matrix, n, m);
		}

		// left
		if (isValid(y, x-1, matrix, n, m)) {
			dfs(y, x-1, matrix, n, m);
		}

		// right
		if (isValid(y, x+1, matrix, n, m)) {
			dfs(y,  x+1, matrix, n, m);
		}
	}

	static boolean isValid(int y, int x, int [][] matrix, int n, int m) {
		if (y < 0 || y >= n) {
			return false;
		} else if (x < 0 || x >= m) {
			return false;
		}

		return matrix[y][x] == 1;
	}
}
