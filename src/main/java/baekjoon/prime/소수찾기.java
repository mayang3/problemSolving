package baekjoon.prime;

import java.util.Arrays;
import java.util.Scanner;

public class 소수찾기 {
	static int MAX_N = 1000;
	static boolean [] isPrime = new boolean[MAX_N+1];

	static {
		Arrays.fill(isPrime, true);
	}

	static void eratosthenes() {
		// 1 은 항상 예외 처리!
		isPrime[0] = isPrime[1] = false;
		int sqrtn = (int)Math.sqrt(MAX_N);

		for (int i=2 ; i<=sqrtn ; i++) {
			// 이 수가 아직 지워지지 않았다면,
			if (isPrime[i]) {
				// i 의 배수 j 들에 대해 isPrime[j]=false 로 둔다.
				// i*i 미만의 배수는 이미 지워졌으므로 신경쓰지 않는다.
				for (int j = i*i ; j<=MAX_N ; j+=i) {
					isPrime[j] = false;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		eratosthenes();

		System.out.println();

		int cnt = 0;

		for (int i=0 ; i<N ; i++) {
			if (isPrime[scanner.nextInt()]) {
				cnt++;
			}
		}

		System.out.println(cnt);
	}
}
