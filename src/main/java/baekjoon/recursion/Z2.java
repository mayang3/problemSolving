package baekjoon.recursion;

import java.util.Scanner;

/**
 * {@link Z} 를 개선한 버전
 */
public class Z2 {
	static int r;
	static int c;

	static int solve(int n, double y, double x) {
		if (n == 0) {
			return 1;
		}

		int sum = 0;
		int div = n/2;

		if (r < y && c < x) {
			// 1 사분면
			sum += solve(div, y-div, x-div);
		} else if (r < y && c > x) {
			// 2 사분면
			sum += solve(div, y-div, x+div) + n * n;
		} else if (r > y && c < x) {
			// 3 사분면
			sum += solve(div, y+div, x-div) + 2 * n * n;
		} else if (r > y && c > x) {
			// 4 사분면
			sum += solve(div, y+div, x+div) + 3 * n * n;
		}

		return sum;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = (int)Math.pow(2, scanner.nextInt());
		r = scanner.nextInt();
		c = scanner.nextInt();

		System.out.println(solve(n/2, n/2-0.5, n/2-0.5)-1);
	}
}
