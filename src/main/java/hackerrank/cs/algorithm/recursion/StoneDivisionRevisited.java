package hackerrank.cs.algorithm.recursion;

import java.util.LinkedList;
import java.util.Scanner;

public class StoneDivisionRevisited {
	static long N;
	static long[] S;

	static int solve(LinkedList<Long> piles) {
		if (complete(piles)) {
			return 0;
		}

		int max = 0;

		for (int i = 0; i < piles.size(); i++) {
			for (int j = 0; j < S.length; j++) {
				long pileSize = piles.get(i);

				if (pileSize % S[j] == 0 && pileSize > S[j]) {
					max = Math.max(max, 1 + solve(makeNextPiles(i, j, piles)));
				}
			}
		}

		return max;
	}

	static LinkedList<Long> makeNextPiles(int i, int j, LinkedList<Long> piles) {
		LinkedList<Long> newPiles = new LinkedList<Long>();

		for (long v : piles) {
			newPiles.add(v);
		}

		long y = newPiles.remove(i);
		long x = S[j];

		long quotient = y/x;

		for (long k=0 ; k<quotient ; k++) {
			newPiles.add(x);
		}

		return newPiles;
	}

	private static boolean complete(LinkedList<Long> list) {
		for (long v : list) {
			for (long s : S) {
				if (v % s == 0 && v > s) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 1
	 * 12 3
	 * 2 3 4
	 *
	 * 4
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int q = scanner.nextInt();

		while (q-- > 0) {
			N = scanner.nextLong(); // the size of initial pile in the query
			int m = scanner.nextInt(); // the size of the set in the query

			S = new long[m];

			for (int i = 0; i < m; i++) {
				S[i] = scanner.nextLong();
			}

			LinkedList<Long> input = new LinkedList<Long>();
			input.add(N);

			System.out.println(solve(input));
		}
	}
}
