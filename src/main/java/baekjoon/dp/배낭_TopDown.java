package baekjoon.dp;

import java.util.Scanner;

public class 배낭_TopDown {
	static Integer [][] dp;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int c = scanner.nextInt();

		int [] weights = new int[n];
		int [] values = new int[n];

		dp = new Integer[n+1][c+1];

		for (int i = 0; i < n; i++) {
			weights[i] = scanner.nextInt();
			values[i] = scanner.nextInt();
		}

		System.out.println(solve(weights, values, c, 0));
	}

	static int solve(int[] weights, int[] values, int capacity, int current) {
		if (capacity <= 0 || current < 0 || current >= weights.length) {
			return 0;
		}

		int value1 = 0;

		if (dp[current][capacity] != null) {
			return dp[current][capacity];
		}

		// current 번째거를 선택한 경우의 최대 가치
		if (weights[current] <= capacity) {
			value1 = values[current] + solve(weights, values, capacity - weights[current], current + 1);
		}

		// current 번째것을 선택하지 않은 경우의 최대 가치
		int value2 = solve(weights, values, capacity, current + 1);

		//
		return dp[current][capacity] = Math.max(value1, value2);
	}
}
