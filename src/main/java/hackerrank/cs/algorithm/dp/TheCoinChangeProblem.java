package hackerrank.cs.algorithm.dp;

import java.util.*;

/**
 * @author baejunbeom
 */
public class TheCoinChangeProblem {
	static long [] cache;

	/**
	 * 잘못된 최초의 예
	 *
	 * 중복된 부분까지 모두 세고 있다.
	 *
	 * 맨 처음 트리의 좌측 노드들을 모두 센 후, 다음 노드들까지 전부 합산하고 있다.
	 *
	 * @param n
	 * @param c
	 * @return
	 */
	static long getWays(int n, int[] c) {
		if (n == 0) {
			return 1;
		} else if (n < 0) {
			return 0;
		}

		if (cache[n] != -1) {
			return cache[n];
		}

		long sum = 0L;

		for (int i=0 ; i<c.length ; i++) {
			sum += getWays(n - c[i], c);
		}

		return cache[n] = sum;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		cache = new long[n+1];

		int m = in.nextInt();
		int[] c = new int[m];

		for(int c_i=0; c_i < m; c_i++){
			c[c_i] = in.nextInt();
		}

		long ways = getWays(n, c);

		System.out.println(ways);
	}
}
