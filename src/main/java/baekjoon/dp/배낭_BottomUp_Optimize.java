package baekjoon.dp;

import java.util.Scanner;

public class 배낭_BottomUp_Optimize {
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
		int [][] dp = new int[2][capacity+1];

		for (int c = 1; c <= capacity; c++) {
			if (weights[0] <= c) {
				dp[0][c] = profits[0];
			}
		}

		int n = profits.length;

		for (int i = 1; i < n; i++) {
			for (int c = 1; c <= capacity; c++) {
				int profit1 = 0;

				if (weights[i] <= c) {
					profit1 = profits[i] + dp[(i+1)%2][c - weights[i]];
				}

				int profit2 = dp[(i+1)%2][c];

				dp[i%2][c] = Math.max(profit1, profit2);
			}
		}

		return dp[(n+1)%2][capacity];
	}
}
