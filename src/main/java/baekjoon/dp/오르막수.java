package baekjoon.dp;

import java.util.Scanner;

public class 오르막수 {
	private static final int M = 10007;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		// n 자리수에 자리가 i 인 dp
		int [][] dp = new int[1001][11];

		int sum = 0;

		// n 자리수이고 끝자리가 i 인 오르막수의 개수들을 다 더해준다.
		for (int i = 0; i < 10; i++) {
			sum = (sum + solve(dp, n, i)) % M;
		}

		System.out.println(sum);
	}

	/**
	 *
	 * @param n
	 * @param i
	 * @return
	 */
	static int solve(int [][] dp, int n, int i) {
		if (n == 1) {
			return 1;
		}

		if (dp[n][i] != 0) {
			return dp[n][i];
		}

		// n 자리수이고 끝자리수가 j 인 오르막수를 구하려면 다 더한 후에,
		// 0~i 까지의 가짓수를 모두 더해줘야 끝자리가 i 인 총 가짓수가 나온다.
		for (int j = 0; j <= i; j++) {
			// 현재 n 자리의 끝자리가 j 인 수의 오르막수 가짓수는 n-1 의 끝자리가 j 인 가짓수 + n 자리수의 j-1 까지의 가짓수이다.
			dp[n][i] = (dp[n][i] + solve(dp, n-1, j)) % M;
		}

		return dp[n][i];
	}

}
