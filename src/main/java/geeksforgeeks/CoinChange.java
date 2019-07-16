package geeksforgeeks;

import java.util.Scanner;

/**
 * @author neo82
 */
public class CoinChange {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int t = scanner.nextInt();

		while (t-- > 0) {
			int m = scanner.nextInt();

			int [] coins = new int[m];

			for (int i = 0; i < m; i++) {
				coins[i] = scanner.nextInt();
			}

			int n = scanner.nextInt();

			Integer [][] dp = new Integer[coins.length][n+1];

			System.out.println(solve(dp, coins, n, 0));
		}
	}

	private static int solve(Integer [][] dp, int[] coins, int n, int currentIndex) {
		int len = coins.length;

		if (n == 0) {
			return 1;
		} else if (currentIndex >= len) {
			return 0;
		}

		if (dp[currentIndex][n] != null) {
			return dp[currentIndex][n];
		}

		int count1 = 0;

		if (coins[currentIndex] <= n) {
			count1 = solve(dp, coins, n-coins[currentIndex], currentIndex);
		}

		int count2 = solve(dp, coins, n, currentIndex+1);

		return dp[currentIndex][n] = count1 + count2;
	}
}
