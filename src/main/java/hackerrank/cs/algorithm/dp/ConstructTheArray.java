package hackerrank.cs.algorithm.dp;

import java.util.Arrays;
import java.util.Scanner;

public class ConstructTheArray {

	static final long MOD = 1000000000 + 7;

	static int n;
	static int k;
	static int x;
	static long [][] cache;

	/**
	 * 완전 탐색..
	 *
	 * stackoverflow or heap memory over 오류 남..
	 *
	 * @param i
	 * @param v
	 * @return
	 */
	static long countArray(int i, int v) {
		if (i == (n - 2)) {
			return v == x ? 0 : 1;
		}

		if (cache[i][v] != -1) {
			return cache[i][v];
		}

		long count = 0;

		for (int j=1 ; j<=k ; j++) {
			if (j != v) {
				count = (count + countArray(i+1, j)) % MOD;
			}
		}

		return cache[i][v] = count;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		k = in.nextInt();
		x = in.nextInt();

		cache = new long[n+1][k+1];

		for (long [] c : cache) {
			Arrays.fill(c, -1);
		}

		System.out.println(countArray(0, 1));
		in.close();
	}
}
