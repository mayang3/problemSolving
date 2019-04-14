package baekjoon.dp;

import java.util.Scanner;

public class PowSum {

	static int solve(int n) {
		if (n==0) {
			return 1;
		} else if (n<0) {
			return 0;
		}

		int min = 987654321;

		for (int i=(int)Math.sqrt(n) ; i>=1 ; i--) {
			min = Math.min(min, 1 + solve(n-(int)Math.pow(i, 2)));
		}

		return min;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		System.out.println(solve(n));
	}
}
