package hackerrank.cs.algorithm.dp;

import java.util.*;

/**
 * 최초 풀이.. 절반정도 Accept..
 *
 * 풀이는 맞으나.. StackOverflow 걸리는듯..
 */
public class MaxArraySum {
	static int n;
	static int [] arr;
	static long [] cache;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		n = scanner.nextInt();

		arr = new int[n];
		cache = new long[n];

		Arrays.fill(cache, Long.MIN_VALUE);

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		long max = Long.MIN_VALUE;

		for (int i = 0; i < n - 2; i++) {
			max = Math.max(max, solve(i));
		}

		System.out.println(max);
	}

	static long solve(int i) {
		if (i == n-1 || i == n-2) {
			return arr[i];
		}

		if (cache[i] != Long.MIN_VALUE) {
			return cache[i];
		}

		long max = Long.MIN_VALUE;

		for (int j = i+2; j < n; j++) {
			max = Math.max(max, Math.max(arr[i], arr[i] + solve(j)));
		}

		return cache[i] = max;
	}
}
