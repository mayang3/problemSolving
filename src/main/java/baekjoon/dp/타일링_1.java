package baekjoon.dp;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/11726
 */
public class 타일링_1 {

	static Integer [][] dp;
	private static final int M = 10007;
	static int cnt = 0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		boolean [][] tiles = new boolean[2][n];
		dp = new Integer[3][n+1];

		System.out.println(solve(tiles, n));
	}

	private static int solve(boolean[][] tiles, int n) {
		YX yx = findNextYx(tiles);

		if (yx == null) {
			return 1;
		}

		int y = yx.y;
		int x = yx.x;

		if (dp[y][x] != null) {
			return dp[y][x];
		}

		int cnt = 0;

		// 가로로 놓는 경우
		if (isValid(y, x+1, n)) {
			tiles[y][x] = tiles[y][x+1] = true;
			cnt += solve(tiles, n) % M;
			tiles[y][x] = tiles[y][x+1] = false;
		}

		// 세로로 놓는 경우
		if (isValid(y+1, x, n)) {
			tiles[y][x] = tiles[y+1][x] = true;
			cnt += solve(tiles, n) % M;
			tiles[y][x] = tiles[y+1][x] = false;
		}

		return dp[y][x] = (cnt % M);
	}

	private static YX findNextYx(boolean[][] tiles) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j] == false) {
					return new YX(i, j);
				}
			}
		}

		return null;
	}

	static class YX {
		int y;
		int x;

		YX(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static boolean isValid(int y, int x, int n) {
		if (y < 0 || y >= 2) {
			return false;
		} else if (x < 0 || x >= n) {
			return false;
		}

		return true;
	}

	private static boolean allMarked(boolean[][] tiles) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j] == false) {
					return false;
				}
			}
		}

		return true;
	}
}
