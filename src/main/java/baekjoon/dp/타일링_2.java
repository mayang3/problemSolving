package baekjoon.dp;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/11727
 */
@SuppressWarnings("ALL")
public class 타일링_2 {
	static int n;
	static final int M = 10007;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		n = scanner.nextInt();

		Integer [][] dp = new Integer[2][n];
		boolean [][] tiles = new boolean[2][n];

		System.out.println(solve(dp, tiles));
	}

	private static int solve(Integer[][] dp, boolean [][] tiles) {
		YX yx = findNext(tiles);

		if (yx == null) {
			return 1;
		}

		int y = yx.y;
		int x = yx.x;

		if (dp[y][x] != null) {
			return dp[y][x];
		}

		int cnt = 0;

		// 1. 2 * 1 tile

		// 1-1. 가로
		if (isValid(y, x+1)) {
			tiles[y][x] = tiles[y][x+1] = true;
			cnt += solve(dp, tiles) % M;
			tiles[y][x] = tiles[y][x+1] = false;
		}

		// 1-2. 세로
		if (isValid(y+1, x)) {
			tiles[y][x] = tiles[y+1][x] = true;
			cnt += solve(dp, tiles) % M;
			tiles[y][x] = tiles[y+1][x] = false;
		}

		// 2 * 2 tile
		if (isValid(y+1, x) && isValid(y, x+1) && isValid(y+1, x+1)) {
			tiles[y][x] = tiles[y+1][x] = tiles[y][x+1] = tiles[y+1][x+1] = true;
			cnt += solve(dp, tiles) % M;
			tiles[y][x] = tiles[y+1][x] = tiles[y][x+1] = tiles[y+1][x+1] = false;
		}

		return dp[y][x] = cnt % M;
	}

	private static boolean isValid(int y, int x) {
		if (y < 0 || y >= 2) {
			return false;
		} else if (x < 0 || x >= n) {
			return false;
		}

		return true;
	}

	private static YX findNext(boolean[][] dp) {

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				if (dp[i][j] == false) {
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
}
