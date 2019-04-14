package baekjoon.fibonacci;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/2747 (int 로 풀림)
 * https://www.acmicpc.net/problem/2748 (long 으로 풀림)
 */
public class 피보나치수 {
	static long [] dp = new long[91];

	static {
		Arrays.fill(dp, -1);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		long ret = solve(n);
		System.out.println(ret);
	}

	static long solve(int n) {
		if (n == 0 || n == 1) {
			return dp[n] = n;
		}

		if (dp[n] != -1) {
			return dp[n];
		}

		return dp[n] = (solve(n-1) + solve(n-2));
	}
}
