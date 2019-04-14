package hackerrank.mathematics.fundamentals;

import java.util.Scanner;

/**
 * 경우의 수 찾기 문제
 *
 * https://www.hackerrank.com/challenges/connecting-towns/problem
 */
public class ConnectingTowns {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int n = scanner.nextInt();

			long ans = 1;

			for (int i = 0; i < n -1; i++) {
				ans = (ans *scanner.nextInt()) % 1234567L;
			}

			System.out.println(ans);
		}
	}
}
