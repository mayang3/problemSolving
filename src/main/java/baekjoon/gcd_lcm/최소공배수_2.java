package baekjoon.gcd_lcm;

import java.util.Scanner;

public class 최소공배수_2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		long a = scanner.nextLong();
		long b = scanner.nextLong();

		System.out.println(a*b / gcd(a,b));
	}

	private static long gcd(long p, long q) {
		if (q == 0) {
			return p;
		}

		return gcd(q, p%q);
	}
}
