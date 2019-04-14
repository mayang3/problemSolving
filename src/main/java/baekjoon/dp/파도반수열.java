package baekjoon.dp;

import java.util.Scanner;

public class 파도반수열 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int n = scanner.nextInt();

			if (n<4) {
				System.out.println(1);
				continue;
			}

			long [] dp = new long[n+1];

			dp[1] = dp[2] = dp[3] = 1;

			for (int i=4 ; i<=n ; i++) {
				dp[i] = dp[i-3] + dp[i-2];
			}

			System.out.println(dp[n]);
		}
	}
}
