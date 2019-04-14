package baekjoon.prime;

import java.util.Arrays;
import java.util.Scanner;

public class 베르트랑공준 {
	static int MAX_N = 123456 * 2;
	static boolean [] isPrime = new boolean[MAX_N*2+1];

	static {
		Arrays.fill(isPrime, true);
	}

	static void eratosthenes() {
		isPrime[0] = isPrime[1] = false;

		int sqrtn = (int)Math.sqrt(MAX_N);

		for (int i=2 ; i<=sqrtn ; i++) {
			if (isPrime[i]) {
				for (int j=i*i ; j<=MAX_N ; j+=i) {
					isPrime[j] = false;
				}
			}
		}

	}


	public static void main(String[] args) {
		eratosthenes();

		Scanner scanner = new Scanner(System.in);

		while (true) {
			int N = scanner.nextInt();

			if (N == 0) {
				break;
			}

			int cnt = 0;

			for (int i=N+1 ; i<=2*N ; i++) {
				if (isPrime[i]) {
					cnt++;
				}
			}

			System.out.println(cnt);
		}
	}
}
