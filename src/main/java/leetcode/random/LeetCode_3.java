package leetcode.random;

import java.util.Scanner;

public class LeetCode_3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();

		char [][] grid = new char[n][m];

		for (int y = 0; y < n; y++) {
			String line = scanner.next();

			for (int x = 0; x < line.length(); x++) {
				grid[y][x] = line.charAt(x);
			}
		}

		System.out.println(numIslands(grid));
	}


	public static int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		int n = grid.length;
		int m = grid[0].length;

		boolean [][] visited = new boolean[n][m];

		int count = 0;

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < m; x++) {
				if (isVaild(grid, visited, y, x, n,m)) {
					count++;
					dfs(grid, visited, y, x, n, m);
				}
			}
		}

		return count;
	}

	private static void dfs(char[][] grid, boolean[][] visited, int y, int x, int n, int m) {
		visited[y][x] = true;

		// top
		if (isVaild(grid, visited, y-1, x, n, m)) {
			dfs(grid, visited, y-1, x, n, m);
		}

		// left
		if (isVaild(grid, visited, y, x-1, n, m)) {
			dfs(grid, visited, y, x-1, n, m);
		}

		// right
		if (isVaild(grid, visited, y, x+1, n, m)) {
			dfs(grid, visited, y, x+1, n, m);
		}

		// bottom
		if (isVaild(grid, visited, y+1, x, n, m)) {
			dfs(grid, visited, y+1, x, n, m);
		}
	}

	private static boolean isVaild(char[][] grid, boolean[][] visited, int y, int x, int n, int m) {
		if (y < 0 || y >= n) {
			return false;
		} else if (x < 0 || x >= m) {
			return false;
		} else if (visited[y][x]) {
			return false;
		} else if (grid[y][x] == '0') {
			return false;
		}

		return true;
	}

}
