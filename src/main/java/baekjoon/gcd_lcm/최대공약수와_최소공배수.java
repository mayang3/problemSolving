package baekjoon.gcd_lcm;

import java.util.Scanner;

public class 최대공약수와_최소공배수 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int a = scanner.nextInt();
		int b = scanner.nextInt();

		int gcd = gcd(a, b);

		System.out.println(gcd);
		System.out.println(a*b / gcd);

	}

	static int gcd(int p, int q) {
		if (q == 0) {
			return p;
		}

		return gcd(q, p%q);
	}
}
