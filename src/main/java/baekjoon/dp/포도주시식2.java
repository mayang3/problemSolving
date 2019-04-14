package baekjoon.dp;

import java.util.Scanner;

/**
 * 3가지 중 하나를 골라야 한다.
 *
 * 1. n 번째를 안 먹는 경우 : dp[n] = dp[n-1]
 * 2. n 번째를 먹고, n-1 번째를 안먹으며, 가능한 이전의 최대치를 이용하는 경우 : dp[n] = wine[i] + dp[n-2]
 * 3. n 번째를 먹고, n-2 번째를 먹으며, 가능한 이전의 최대치를 이용하는 경우 : dp[n] = wine[i] + wine[i-1] + dp[n-3]
 *
 */
public class 포도주시식2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int [] arr = new int[n+2];

		for (int i=1 ; i<=n ; i++) {
			arr[i] = scanner.nextInt();
		}

		// NPE 방지 위해서 n+2 로 설정
		int [] dp = new int[n+2];

		dp[0] = 0;
		dp[1] = arr[1];
		dp[2] = arr[1] + arr[2];

		for (int i=3 ; i<=n ; i++) {
			dp[i] = Math.max(dp[i], dp[i-1]);
			dp[i] = Math.max(dp[i], arr[i] + dp[i-2]);
			dp[i] = Math.max(dp[i], arr[i] + arr[i-1] + dp[i-3]);
		}

		System.out.println(dp[n]);
	}
}
