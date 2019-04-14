package baekjoon.dp;

import java.util.Scanner;

@SuppressWarnings("ALL")
public class 배낭_BottomUp_Optimize2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int c = scanner.nextInt();

		int [] weights = new int[n];
		int [] profits = new int[n];

		for (int i = 0; i < n; i++) {
			weights[i] = scanner.nextInt();
			profits[i] = scanner.nextInt();
		}

		System.out.println(solveKnapsack(profits, weights, c));
	}

	static int solveKnapsack(int[] profits, int[] weights, int capacity) {

		int [] dp = new int[capacity+1];
		int n = profits.length;

		for (int c = 1; c <= capacity; c++) {
			if (weights[0] <= c) {
				dp[c] = profits[0];
			}
		}

		for (int i = 1; i < n; i++) {
			for (int c = capacity; c >= 0; c--) {
				int profit1 = 0;

				if (weights[i] <= c) {
					profit1 = profits[i] + dp[c - weights[i]];
				}

				int profit2 = dp[c];

				dp[c] = Math.max(profit1, profit2);
			}
		}

		return dp[capacity];
	}
}
