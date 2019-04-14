package strategies.dp.numberOfCase;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class Poly {
	private static final long MOD = 10 * 1000 * 1000;
	private static int [][] cache = new int[101][101];

	static int poly(int n, int first) {
		// base case : n == first
		if (n == first) {
			return 1;
		}

		if (cache[n][first] != -1) {
			return cache[n][first];
		}

		cache[n][first] = 0;

		for (int second = 1; second <= n-first ; second++) {
			long add = second + first - 1;
			add *= poly(n - first, second);
			add %= MOD;
			cache[n][first] += add;
			cache[n][first] %= MOD;
		}

		return cache[n][first];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cases = sc.nextInt();
		while(cases-- > 0) {

			for (int [] c : cache) {
				Arrays.fill(c, -1);
			}

			int n = sc.nextInt();
			int sum = 0;
			int first = 1;

			for (int second = 1; second <= n-first ; second++) {
				sum += poly(n - first, second);
			}

			System.out.println(sum);
		}

	}
}
