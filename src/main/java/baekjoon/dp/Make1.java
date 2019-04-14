package baekjoon.dp;

import java.util.Arrays;
import java.util.Scanner;

public class Make1 {

	static final int INF = 987654321;
	static int [] cache;

	/**
	 * stack overflow
	 * @param n
	 * @return
	 */
	static int solve(int n) {

		if (n==1) {
			return 0;
		}

		if (cache[n] != INF) {
			return cache[n];
		}

		for (int i=1 ; i<=n ; i++) {
			// 3으로 나누어 떨어지는 경우
			if (n/3 > 0 && n%3 == 0) {
				cache[n] = Math.min(cache[n], 1+solve(n/3));
			}

			// 2로 나누어 떨어지는 경우
			if (n/2 > 0 && n%2 == 0) {
				cache[n] = Math.min(cache[n], 1+solve(n/2));
			}

			// 그 외의 경우
			cache[n] = Math.min(cache[n], 1+solve(n-1));
		}

		return cache[n];
	}

	/**
	 * accepted..
	 *
	 * @param n
	 * @return
	 */
	static int loopSolve(int n) {
		cache[1] = 0;

		for (int i=2 ; i<=n ; i++) {
			if (i/3 > 0 && i%3 == 0) {
				cache[i] = Math.min(cache[i], cache[i/3] + 1);
			}

			if (i/2 > 0 && i%2 == 0) {
				cache[i] = Math.min(cache[i], cache[i/2] + 1);
			}

			if (i>1) {
				cache[i] = Math.min(cache[i], cache[i-1] + 1);
			}
		}

		return cache[n];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		cache = new int[n+1];

		Arrays.fill(cache, INF);

		System.out.println(loopSolve(n));
	}
}
