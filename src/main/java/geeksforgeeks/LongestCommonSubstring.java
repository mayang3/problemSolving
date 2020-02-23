package geeksforgeeks;

import java.util.Scanner;

public class LongestCommonSubstring {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int t = scanner.nextInt();

		while (t-- > 0) {
			int n = scanner.nextInt();
			int m = scanner.nextInt();

			String s1 = scanner.next();
			String s2 = scanner.next();

			System.out.println(solve(s1, s2, n, m));
		}
	}

	static int solve(String s1, String s2, int n, int m) {
		int [][] dp = new int[n+1][m+1];

		int max = 0;

		for (int y = 1; y < n+1; y++) {
			for (int x = 1; x < m+1; x++) {
				if (s1.charAt(y-1) == s2.charAt(x-1)) {
					dp[y][x] = 1 + dp[y-1][x-1];
					max = Math.max(max, dp[y][x]);
				}
			}
		}

		return max;
	}
}
