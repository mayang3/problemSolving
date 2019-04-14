package hackerrank.mathematics.fundamentals;

import java.util.Scanner;

/**
 * TODO 복습
 *
 * https://www.hackerrank.com/challenges/leonardo-and-prime/problem
 *
 * Leonardo loves primes and created q queries where each query takes the form of an integer, n.
 *
 * For each n, he wants you to count the maximum number of unique prime factors of any number in the inclusive range [1,n]
 *
 * and then print this value on a new line.
 *
 * Note. Recall that a prime number is only divisible by 1 and itself, and 1 is not a prime number.
 *
 *
 * The maximum number of unique prime factors in the inclusive range
 * inclusive range 의 소인수들의 최대의 수를 구하라는 의미.
 *
 * 문제 자체가 잘 이해안됨..... 아래 풀이대로 풀어서 accept 는 나왔다.
 *
 * 풀이 내용은, 각 소수들의 곱이 N 보다 작을때까지 곱해서, 그때까지의 N 의 수가 maximum number of unique prime factor 라고 한다.
 *
 *
 *
 */
public class LeonardoPrimeFactors {
	static long [] primes = {2 , 3 , 5 , 7 , 11 , 13 , 17 , 19 , 23 , 29 , 31 , 37 , 41 , 43 , 47};

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int q = scanner.nextInt();

		while (q-- > 0) {
			long n = scanner.nextLong();
			long product = 1;
			long ans = 0;

			for (int i = 0; i < 15; i++) {
				product *= primes[i];

				if (product <= n) {
					ans++;
				}
			}

			System.out.println(ans);
		}
	}

}
