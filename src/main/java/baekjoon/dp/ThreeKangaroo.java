package baekjoon.dp;

import java.util.Scanner;

public class ThreeKangaroo {

	static int solve(int A, int B, int C) {
		return Math.max(C-B-1, B-A-1);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int A = scanner.nextInt();
		int B = scanner.nextInt();
		int C = scanner.nextInt();

		System.out.println(solve(A,B,C));
	}
}
