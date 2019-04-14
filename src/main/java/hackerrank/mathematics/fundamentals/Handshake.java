package hackerrank.mathematics.fundamentals;

import java.util.Scanner;

/**
 * \displaystyle\sum_{i=0}^{n-1} S_i
 * Si 는 n 명이 악수를 하는 총 횟수이다.
 */
public class Handshake {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int n = scanner.nextInt();

			int sum = 0;

			for (int i = 0; i < n; i++) {
				sum += i;
			}

			System.out.println(sum);
		}
	}
}
