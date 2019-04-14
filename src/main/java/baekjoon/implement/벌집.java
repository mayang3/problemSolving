package baekjoon.implement;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/2292
 */
public class 벌집 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		if (N == 1) {
			System.out.println(1);
			return;
		}

		long sum = 1;
		int cnt = 2;

		for (int i=1 ; i<1000000000 ; i++) {
			if (sum > 1000000000) {
				break;
			}

			sum = sum + (6*i);

			if (N <= sum) {
				System.out.println(cnt);
				return;
			}

			cnt++;
		}

	}
}
