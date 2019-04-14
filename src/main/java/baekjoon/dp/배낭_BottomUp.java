package baekjoon.dp;

import java.util.Scanner;

public class 배낭_BottomUp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int c = scanner.nextInt();

		int [] weights = new int[n];
		int [] values = new int[n];

		for (int i = 0; i < n; i++) {
			weights[i] = scanner.nextInt();
			values[i] = scanner.nextInt();
		}

		System.out.println(solve(weights, values, n, c));
	}

	static int solve(int[] weights, int[] values, int n, int maxCapacity) {
		int [][] dp = new int[n][maxCapacity+1];

		for (int i = 0; i < n; i++) {
			dp[i][0] = 0;
		}

		for(int c=0; c <= maxCapacity; c++) {
			if(weights[0] <= c)
				dp[0][c] = values[0];
		}

		for (int i = 1; i < n; i++) {
			for (int capacity = 0; capacity <= maxCapacity; capacity++) {
				int profit1 = 0;

				if (weights[i] <= capacity) {
					profit1 = values[i] + dp[i-1][capacity-weights[i]];
				}

				int profit2 = dp[i-1][capacity];

				dp[i][capacity] = Math.max(profit1, profit2);
			}
		}

		return dp[n-1][maxCapacity];
	}
}
