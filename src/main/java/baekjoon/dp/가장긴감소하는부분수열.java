package baekjoon.dp;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/11722
 */
@SuppressWarnings("Duplicates")
public class 가장긴감소하는부분수열 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int [] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		int max = 0;

		for (int start = 0; start < n; start++) {
			max = Math.max(max, solve(new Integer[n], arr, start));
		}

		System.out.println(max);
	}

	static int solve(Integer [] dp, int[] arr, int start) {
		int n = arr.length;

		if (start == n-1) {
			return 1;
		}

		if (dp[start] != null) {
			return dp[start];
		}

		int len = 1;

		for (int i = start+1; i < n; i++) {
			if (arr[start] > arr[i]) {
				len = Math.max(len, 1 + solve(dp, arr, i));
			}
		}

		return dp[start] = len;
	}
}
