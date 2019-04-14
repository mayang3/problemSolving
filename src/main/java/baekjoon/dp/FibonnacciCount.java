package baekjoon.dp;

import java.util.Scanner;

public class FibonnacciCount {
	static CacheCount [] cache;

	static class CacheCount {
		long zeroCount;
		long oneCount;

		CacheCount(long zeroCount, long oneCount) {
			this.zeroCount = zeroCount;
			this.oneCount = oneCount;
		}

	}

	static CacheCount fibonacci(int n) {
		if (cache[n] != null) {
			return cache[n];
		}

		if (n == 0) {
			return new CacheCount(1, 0);
		} else if (n == 1) {
			return new CacheCount(0, 1);
		}

		CacheCount fibonacci = fibonacci(n - 1);
		CacheCount fibonacci2 = fibonacci(n - 2);

		return cache[n] = new CacheCount(fibonacci.zeroCount + fibonacci2.zeroCount, fibonacci.oneCount + fibonacci2.oneCount);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		while (T-- > 0) {
			int n = scanner.nextInt();

			cache = new CacheCount[n + 1];

			CacheCount ret = fibonacci(n);
			System.out.println(ret.zeroCount + " " + ret.oneCount);

		}
	}
}
