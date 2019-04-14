package baekjoon.binomial;

import java.util.Scanner;

@SuppressWarnings("ALL")
public class 이항계수_2 {
	static int M = 10007;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int K = scanner.nextInt();

		// modInv(b,M)=b^m-2 % M
		// (a/b)%M = (a*modInv(b,M))%M
		System.out.println((factorial(N) * modInverse(factorial(K) * factorial(N-K), M)) % M);

	}

	static int factorial(int num) {
		int ret = 1;

		for (int i=2 ; i<=num ; i++) {
			ret = ((ret * i) % M);
		}

		return ret;
	}

	static int modInverse(int a, int m) {
		a = a % M;

		int m0 = m;
		int y =0, x=1;

		if (m == 1) {
			return 0;
		}

		while (a > 1) {
			// q is quotient
			int q = a / m;
			int t = m;

			// m is remainder now, process
			// same as Euclid's algo
			m = a % m;
			a = t;
			t = y;

			// Update x and y
			y = x - q * y;
			x = t;
		}

		// Make x positive
		if (x < 0) {
			x += m0;
		}

		return x;
	}
}
