package baekjoon.binomial;

import java.util.Scanner;

/**
 * 0 factorial is 1 이라는 것만 유의하자.
 */
public class 팩토리얼 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		System.out.println(factorial(N));
	}

	static int factorial(int n) {
		int ret = 1;

		for (int i=2 ; i<=n ; i++) {
			ret *= i;
		}

		return ret;
	}
}
