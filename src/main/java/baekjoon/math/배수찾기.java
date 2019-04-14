package baekjoon.math;

import java.util.Scanner;

/**
 */
public class 배수찾기 {
	static final String OK = "%d is a multiple of %d.";
	static final String NO = "%d is NOT a multiple of %d.";


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int num = 0;

		do {
			num = scanner.nextInt();

			if (num == 0) {
				break;
			}

			if (num % n == 0) {
				System.out.println(String.format(OK, num, n));
			} else {
				System.out.println(String.format(NO, num, n));
			}

		} while (num != 0);
	}
}
