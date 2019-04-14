package baekjoon.math;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1010
 */
public class 다리놓기 {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int n = scanner.nextInt();
			int m = scanner.nextInt();

			System.out.println(nPr(m, n));
		}
	}

	static long nPr(int n, int m) {
		if (n == 0 || m == 0) {
			return 0;
		}

		if (n == m) {
			return 1;
		}

		long ret = 1;

		for (int i = 0; i < m; i++) {
			ret *= n--;
		}

		return ret;
	}
}
