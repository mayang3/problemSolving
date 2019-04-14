package hackerrank.cs.algorithm.implement;

import java.util.Scanner;

public class AbsolutePermutation {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int n = scanner.nextInt();
			int k = scanner.nextInt();

			if (k == 0) {
				print(n);
			} else {
				solve(n, k);
			}

			System.out.println();
		}
	}

	static void print(int n) {
		for (int i=1 ; i<=n ; i++) {
			System.out.print(i + " ");
		}
	}

	static void solve(int n, int k) {

		int [] ret = new int[n];

		int a1=1;
		int i = 0;

		while (a1 <=n) {

			for (int a2 = a1 + k; a2 < a1 + k + k; a2++) {
				if (a2 <= 0 || a2 > n) {
					System.out.print("-1");
					return;
				}

				ret[i++] = a2;
			}

			for (int a3 = a1; a3 < a1 + k; a3++) {
				if (a3 <= 0 || a3 > n) {
					System.out.print("-1");
					return;
				}

				ret[i++] = a3;
			}

			a1 += (2 * k);
		}

		for (int v : ret) {
			System.out.print(v + " ");
		}
	}
}
