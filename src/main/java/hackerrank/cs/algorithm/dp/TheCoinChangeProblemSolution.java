package hackerrank.cs.algorithm.dp;

import java.util.Scanner;

/**
 */
public class TheCoinChangeProblemSolution {

	static int [] c;
	static long [][] table = new long[52][252]; // i 는 코인의 갯수 + 2, make 는 n 의 갯수 + 2
	static boolean [][] calculated = new boolean[52][252];
	static int numCoins;

	/**
	 *
	 * @param i
	 * @param make
	 * @return
	 */
	static long getWays(int i, int make) {
		if (make < 0) return 0;
		if (make == 0) return 1;
		if (i > numCoins) return 0;

		// 같은 로직의 중복 계산을 막는다.
		if (calculated[i][make] == false) {
			// i번째의 코인을 가지고 make의 숫자를 만드는 방법의 수
			table[i][make] = getWays(i, make - c[i]) + getWays(i+1, make);
			calculated[i][make] = true;
		}

		// dp value return
		return table[i][make];
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		numCoins = m;
		c = new int[m+1];

		for(int c_i=1; c_i <= m; c_i++){
			c[c_i] = in.nextInt();
		}

		long ways = getWays(1, n);

		System.out.println(ways);
	}
}
