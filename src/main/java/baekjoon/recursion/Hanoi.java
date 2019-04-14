package baekjoon.recursion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Accepted
 */
public class Hanoi {

	static long count = 0;
	static List<String> printList = new ArrayList<>();

	/**
	 * 항상 a->c 로 옮기고, 기둥을 바꾸자.
	 *
	 * @param n
	 * @param a
	 * @param b
	 * @param c
	 */
	static void solve(int n, int a, int b, int c) {
		if (n <= 0) {
			return;
		}

		solve(n-1, a, c, b);

		count++;
		printList.add(a + " " + c);

		solve(n-1, b, a, c);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		if (N > 20) {
			BigDecimal bigDecimal = new BigDecimal(Math.pow(2, N));
			System.out.println(bigDecimal.subtract(new BigDecimal(1)));
			return;
		}

		solve(N, 1,2,3);

		System.out.println(count);
		for (String pr : printList) {
			System.out.println(pr);
		}
	}
}
