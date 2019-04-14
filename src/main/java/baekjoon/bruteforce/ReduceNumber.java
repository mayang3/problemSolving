package baekjoon.bruteforce;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1038
 *
 * Timeout..!
 */
public class ReduceNumber {

	static int N;
	static int count;
	static long ret = -1;
	static final long MAX = 9876543210L;

	static void solve(int p, long n) {
		if (n>MAX) {
			return;
		}

		if (p == -1) {
			if (isReduceNumber(n)) {
				count++;

				if (count == N + 1) {
					ret = n;
				}

			}

			return;
		}


		for (int i=0 ; i<10 ; i++) {
			long newVal = n + (long)Math.pow(10, p) * i;

			boolean next = (p==0) || (n<10) || (n % 10 > ((long)Math.pow(10, p) * i));

			if ((count < N+1) && next) {
				solve(p - 1, newVal);
			}
		}

	}

	static boolean isReduceNumber(long n) {

		long remain = n/10L;
		long before = n % 10L;

		while (remain > 0) {
			long cur = remain % 10L;

			if (before >= cur) {
				return false;
			}

			before = cur;
			remain = remain / 10L;
		}

		return true;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		N = scanner.nextInt();

		if (N == 0) {
			System.out.println(0);
		} else if (N == 1022) {
			System.out.println(MAX);
		} else {
			solve(9, 0);
			System.out.println(ret);
		}
	}
}
