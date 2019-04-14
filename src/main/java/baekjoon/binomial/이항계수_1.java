package baekjoon.binomial;

import java.util.Scanner;

public class 이항계수_1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int K = scanner.nextInt();

		int result = factorial(N) / (factorial(K) * factorial(N-K));

		System.out.println(result);
	}

	static int factorial(int num) {
		int ret = 1;

		for (int i=2 ; i<=num ; i++) {
			ret *= i;
		}

		return ret;
	}
}
