package hackerrank.cs.algorithm.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 */
@SuppressWarnings("ALL")
public class TheCoinChangeProblem2 {
	static long [][] cache;
	static int [] c;

	/**
	 * 캐시는 recursive 트리의 각 단계마다 저장한다.
	 *
	 * 즉, 따지고 보면 트리의 첫번째 좌측 노드의 값만 가져가는것.. (순서가 없기 때문에..)
	 *
	 * @param idx
	 * @param n
	 * @return
	 */
	static long getWays(int idx, int n) {
		if (n == 0) return 1;
		if (n < 0) return 0;
		if (idx >= c.length) return 0;

		// 알고보니..
		// 여기서 함수 getWays(idx, n-c[idx]) + getWays(idx+1, n) 는,
		// 완전 탐색을 하면서 dp 를 효율적으로 맵핑하기 위한 완전 탐색의 recursive 변형에 불과하다.

		// 원래는 아래와 같은 코드를 트리의 각 노드별로 캐싱을 하기 위해 효율적인 구조로 변형하였다.

//		for (int i=0 ; i<c.length ; i++) {
//			getWays(n - c[i], c);
//		}


		if (cache[idx][n] == -1) {
			cache[idx][n] = getWays(idx, n-c[idx]) + getWays(idx+1, n);
		}

		return cache[idx][n];
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		cache = new long[n+1][n+1];

		for (long [] c : cache) {
			Arrays.fill(c, -1);
		}

		int m = in.nextInt();
		c = new int[m];

		for(int c_i=0; c_i < m; c_i++){
			c[c_i] = in.nextInt();
		}

		long ways = getWays(0, n);

		System.out.println(ways);
	}
}
