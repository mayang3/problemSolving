package baekjoon.gcd_lcm;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1934
 */
public class 최소공배수 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();

			System.out.println(a * b / gcd(a, b));
		}
	}

	static int gcd(int p, int q) {
		if (q == 0) {
			return p;
		}

		return gcd(q, p%q);
	}
}
