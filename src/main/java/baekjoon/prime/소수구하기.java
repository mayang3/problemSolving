package baekjoon.prime;

import java.util.Arrays;
import java.util.Scanner;

public class 소수구하기 {
	static int MAX_N = 1000001;
	static boolean [] isPrime = new boolean[MAX_N+1];

	static void eratosthenes() {
		isPrime[0] = isPrime[1] = false;

		int sqrtn = (int)Math.sqrt(MAX_N);

		for (int i=2 ; i<=sqrtn ; i++) {
			if (isPrime[i]) {
				// 여기 낑겨 넣으니까 오답처리됨.. why?
				for (int j=i*i ; j<=MAX_N ; j+=i) {
					isPrime[j] = false;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int M = scanner.nextInt();
		int N = scanner.nextInt();

		Arrays.fill(isPrime, true);

		eratosthenes();

		// 꼭 isPrime 을 완성하고 출력해야된다..
		for (int i=M ; i<=N ; i++) {
			if (isPrime[i]) {
				System.out.println(i);
			}
		}

	}


}
