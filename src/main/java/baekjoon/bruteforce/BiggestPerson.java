package baekjoon.bruteforce;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/7568
 */
public class BiggestPerson {

 	static int [][] xy;

	static void solve() {

		for (int i=0 ; i<xy.length ; i++) {
			int rank = 1;

			for (int j=0 ; j<xy.length ; j++) {
				if (i==j) continue;

				int ix = xy[i][0];
				int iy = xy[i][1];

				int jx = xy[j][0];
				int jy = xy[j][1];

				if (ix < jx && iy < jy) {
					rank++;
				}
			}

			System.out.print(rank + " ");
		}
	}


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();

		xy = new int[n][2];

		for (int i=0 ; i<n ; i++) {
			xy[i][0] = scanner.nextInt();
			xy[i][1] = scanner.nextInt();
		}

		solve();

	}
}
