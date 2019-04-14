package baekjoon.implement;

import java.util.Scanner;

public class 부녀회장이될테야 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int k = scanner.nextInt();
			int n = scanner.nextInt();

			System.out.println(solve(k, n));
		}
	}

	static int solve(int k, int n) {
		if (k == 0) {
			return n;
		}

		int sum = 0;

		for (int i=1 ; i<=n ; i++) {
			sum += solve(k-1, i);
		}

		return sum;
	}
}
