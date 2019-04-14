package hackerrank.cs.algorithm.search;

import java.util.Scanner;

public class ConnectedCellsInAGrid {
	static int [][] matrix;
	static boolean [][] visited;


	static int n;
	static int m;

	static int cnt=0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		n = scanner.nextInt();
		m = scanner.nextInt();

		matrix = new int[n][m];
		visited = new boolean[n][m];

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < m; x++) {
				matrix[y][x] = scanner.nextInt();
			}
		}

		int max = 0;

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < m; x++) {
				if (matrix[y][x] == 1) {
					cnt = 0;
					dfs(y, x);
					max = Math.max(max, cnt);
				}
			}
		}

		System.out.println(max);
	}

	static void dfs(int y, int x) {
		if (isValid(y, x) == false) {
			return;
		}

		visited[y][x] = true;
		matrix[y][x] = 0;
		cnt++;

		// top
		if (isValid(y-1, x)) {
			dfs(y-1, x);
		}

		// top - left
		if (isValid(y-1, x-1)) {
			dfs(y-1, x-1);
		}

		// top - right
		if (isValid(y-1, x+1)) {
			dfs(y-1, x+1);
		}

		// bottom
		if (isValid(y+1, x)) {
			dfs(y+1, x);
		}

		// bottom - left
		if (isValid(y+1, x-1)) {
			dfs(y+1, x-1);
		}

		// bottom - right
		if (isValid(y+1, x+1)) {
			dfs(y+1, x+1);
		}

		// left
		if (isValid(y, x-1)) {
			dfs(y, x-1);
		}

		// right
		if (isValid(y, x+1)) {
			dfs(y, x+1);
		}
	}

	static boolean isValid(int y, int x) {
		if (y < 0 || y >= n) {
			return false;
		} else if (x < 0 || x >= m) {
			return false;
		} else if (matrix[y][x] != 1) {
			return false;
		}

		return true;
	}

}
