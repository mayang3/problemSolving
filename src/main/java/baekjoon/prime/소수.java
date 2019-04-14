package baekjoon.prime;

import java.util.Arrays;
import java.util.Scanner;

public class 소수 {

	static int MAX_N = 10000;
	static boolean [] isPrime = new boolean[MAX_N+1];

	static {
		Arrays.fill(isPrime, true);
	}

	static void eratosthenes() {
		isPrime[0] = isPrime[1] = false;

		int sqrtn = (int)Math.sqrt(MAX_N);

		for (int i=2 ; i<=sqrtn ; i++) {
			if (isPrime[i]) {
				for (int j = i*i ; j<=MAX_N ; j += i) {
					isPrime[j] = false;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int M = scanner.nextInt();
		int N = scanner.nextInt();

		eratosthenes();

		int sum = 0;
		int min = 987654321;

		for (int i=M ; i<=N ; i++) {
			if (isPrime[i]) {
				min = Math.min(min, i);
				sum += i;
			}
		}

		if (sum == 0) {
			System.out.println(-1);
		} else {
			System.out.println(sum);
			System.out.println(min);
		}

	}
}
