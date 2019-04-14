package hackerrank.cs.algorithm.sorting;

import java.util.Scanner;

/**
 * Given a long integer x, count the number of values of a satisfying the following conditions:
 *
 * a ^ x > x
 * 0 < a < x
 *
 * where a and x are long integers and XOR is the bitwise XOR operator.
 *
 * You are given q queries, and each query is in the form of a long integer denoting x.
 *
 * For each query, print the total number of values of a satisfying the conditions above on a new line.
 *
 * For example, you are given the value x = 5. Condition 2 requires that a < x. The following tests are run:
 *
 * 1 ^ 5 = 4
 * 2 ^ 5 = 7
 * 3 ^ 5 = 6
 * 4 ^ 5 = 1
 *
 * We find that there are 2 values meeting the first condition: 2 and 3.
 *
 * Input Format
 *
 * The first line contains an integer q, the number of queries.
 *
 * Each of the next q lines contains a long integer describing the value of x for a query.
 *
 * Constraints
 *
 * 1 <= q <= 10^5
 * 1 <= x <= 10^10
 *
 */
public class TheGreatXOR {
	static long [] cntByPow = new long[36];

	static {
		cntByPow[0] = 1;

		for (int i = 1; i < 36; i++) {
			cntByPow[i] = cntByPow[i-1] * 2;
		}
	}

	/**
	 * 직접 손으로 펼쳐보고 규칙을 찾았다.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int q = scanner.nextInt();

		while (q-- > 0) {
			long x = scanner.nextLong();

			char [] bits = Long.toBinaryString(x).toCharArray();

			long sum = 0;

			for (int i = bits.length - 1; i >= 0; i--) {
				if (bits[i] == '0') {
					sum += cntByPow[bits.length-1-i];
				}
			}

			System.out.println(sum);

		}

	}
}