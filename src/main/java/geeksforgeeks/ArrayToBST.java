package geeksforgeeks;

import java.util.Scanner;

/**
 * @author neo82
 */
public class ArrayToBST {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int t = scanner.nextInt();

		while (t-- > 0) {

			int n = scanner.nextInt();

			int[] A = new int[n];

			for (int i = 0; i < n; i++) {
				A[i] = scanner.nextInt();
			}

			StringBuilder sb = new StringBuilder();

			solve(sb, 0, A.length - 1, A);

			System.out.println(sb.toString().trim());
		}
	}

	static void solve(StringBuilder sb, int start, int end, int [] A) {
		int m = (start + end) / 2;

		sb.append(A[m]).append(" ");

		if (start == end) {
			return;
		}

		if (start < m) {
			solve(sb, start, m - 1, A);
		}

		if (m < end) {
			solve(sb, m + 1, end, A);
		}
	}
}
