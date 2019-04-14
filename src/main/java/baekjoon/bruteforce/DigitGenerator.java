package baekjoon.bruteforce;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/2231
 */
public class DigitGenerator {

	static int N;
	static int min = Integer.MAX_VALUE;

	/**
	 *
	 * @param power
	 * @param value
	 * @return
	 */
	static void solve(int power, int value) {
		if (power == String.valueOf(N).length()) {

			int remain = value;
			int sum = 0;

			for (int i=0 ; i<power ; i++) {
				sum += remain % 10;
				remain /= 10;
			}

			if (value + sum == N) {
				min = Math.min(min, value);
			}

			return;
		}

		for (int i=0 ; i<10 ; i++) {
			int newVal = (int)Math.pow(10, power) * i;
			solve(power+1, value + newVal);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		N = scanner.nextInt();

		solve(0, 0);

		if (Integer.MAX_VALUE == min ) {
			System.out.println(0);
		} else {
			System.out.println(min);
		}
	}
}
