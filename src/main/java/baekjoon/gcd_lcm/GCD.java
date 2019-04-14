package baekjoon.gcd_lcm;

import java.util.Scanner;

public class GCD {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int p = scanner.nextInt();
		int q = scanner.nextInt();

		System.out.println(gcd(p,q));
	}

	// greatest common divisor
	static int gcd(int p, int q) {
		if (q == 0) {
			return p;
		}

		// if p < q 이면, 다음번에 q 는 p 가 되므로 이 경우에 대한 별도 예외처리를 하지 않는다.
		return gcd(q, p % q);
	}
}
