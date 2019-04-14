package baekjoon.dp;

import java.util.Scanner;

public class Plus123 {

	static int solve(int n) {
		if (n==0) {
			return 1;
		} else if (n<0) {
			return 0;
		}

		int count = 0;

		for (int i=1 ; i<=3 ; i++) {
			count += solve(n-i);
		}

		return count;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int n = scanner.nextInt();

			System.out.println(solve(n));
		}
	}
}
