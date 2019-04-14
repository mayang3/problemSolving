package baekjoon.gcd_lcm;

import java.util.Scanner;

/**
 * https://www.geeksforgeeks.org/c-program-find-lcm-two-numbers/
 */
public class LCM {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int a = scanner.nextInt();
		int b = scanner.nextInt();

		System.out.println(a*b / gcd(a,b));
	}


	static int gcd(int p, int q) {
		if (q == 0) {
			return p;
		}

		return gcd(q, p % q);
	}
}
