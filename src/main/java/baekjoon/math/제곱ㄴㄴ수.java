package baekjoon.math;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1016
 *
 * TODO..우선 min ~ max 사이에 소수의 제곱이 몇개가 포함되었는지를 확인하면 답은 나온다..
 *
 * 그럼 어떻게 소수의 제곱의 개수를 빠르게 구할것인가???
 */
public class 제곱ㄴㄴ수 {

	static boolean [] isPrime;

	static void eratosthenes(int start, int end) {
		Arrays.fill(isPrime, true);

		isPrime[0] = isPrime[1] = false;

		int sqrtn = (int)Math.sqrt(end);

		for (int i=start ; i<=sqrtn ; i++) {
			if (isPrime[i]) {
				for (int j=i*i ; j<=end ; j*=i) {
					isPrime[j] = false;
				}
			}
		}
	}


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		long min = scanner.nextLong();
		long max = scanner.nextLong();



	}
}
