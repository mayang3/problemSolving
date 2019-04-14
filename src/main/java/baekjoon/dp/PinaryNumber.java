package baekjoon.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/2193
 */
public class PinaryNumber {

	static long [] cache = new long[91];

	static long solve(int n) {
		if (cache[n] != -1) {
			return cache[n];
		}

		if (n==1 || n==2) {
			cache[n] = 1;
			return 1;
		}

		return cache[n] = (solve(n-1) + solve(n-2));
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		Arrays.fill(cache, -1);

		System.out.println(solve(n));
	}
}
