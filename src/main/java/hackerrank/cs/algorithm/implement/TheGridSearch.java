package hackerrank.cs.algorithm.implement;

import java.util.Scanner;

public class TheGridSearch {
	static String [] G;
	static String [] P;

	static int R;
	static int C;

	static int r;
	static int c;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			R = scanner.nextInt();
			C = scanner.nextInt();

			G = new String[R];

			for (int i=0 ; i<R ; i++) {
				G[i] = scanner.next();
			}

			r = scanner.nextInt();
			c = scanner.nextInt();

			P = new String[r];

			for (int i=0 ; i<r ; i++) {
				P[i] = scanner.next();
			}

			solve();
		}
	}

	static void solve() {
		for (int i=0 ; i<R ; i++) {
			for (int j=0 ; j<C ; j++) {
				if (match(i,j)) {
					System.out.println("YES");
					return;
				}
			}
		}

		System.out.println("NO");
	}

	static boolean match(int i, int j) {
		for (int pi=0 ; pi<r ; pi++) {
			for (int pj=0 ; pj<c ; pj++) {
				if (i+pi >= R || j+pj >= C) {
					return false;
				}

				if (P[pi].charAt(pj) != G[i+pi].charAt(j+pj)) {
					return false;
				}
			}
		}

		return true;
	}
}
