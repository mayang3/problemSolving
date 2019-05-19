package baekjoon.math;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1964
 */
public class 오각형 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int [] dp = new int[2];

		dp[1] = 5;

		for (int i = 2, k = 7; i <= n; i++, k+=3) {
			dp[i % 2] = (dp[(i-1) % 2] + k) % 45678;
		}

		if (n % 2 == 0) {
			System.out.println(dp[0]);
		} else {
			System.out.println(dp[1]);
		}
	}
}
