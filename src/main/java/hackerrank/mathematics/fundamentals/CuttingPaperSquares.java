package hackerrank.mathematics.fundamentals;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/p1-paper-cutting/problem?h_r=next-challenge&h_v=zen
 */
public class CuttingPaperSquares {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		long n = scanner.nextLong();
		long m = scanner.nextLong();

		System.out.println(n * m - 1L);
	}
}
