package leetcode;

import java.util.HashMap;
import java.util.Map;

public class KnightDialer {
	static Map<Integer, int []> DIRECTION_MAP = new HashMap<>();
	static int MOD = (int)1e9 + 7;

	static {
		DIRECTION_MAP.put(0, new int [] {4, 6});
		DIRECTION_MAP.put(1, new int [] {8, 6});
		DIRECTION_MAP.put(2, new int [] {7, 9});
		DIRECTION_MAP.put(3, new int [] {4, 8});
		DIRECTION_MAP.put(4, new int [] {0, 3, 9});
		DIRECTION_MAP.put(5, new int [] {});
		DIRECTION_MAP.put(6, new int [] {0, 1, 7});
		DIRECTION_MAP.put(7, new int [] {2, 6});
		DIRECTION_MAP.put(8, new int [] {1, 3});
		DIRECTION_MAP.put(9, new int [] {2, 4});
	}

	public int knightDialer(int n) {
		if (n == 1) {
			return 10;
		}

		int [][] dp = new int[10][2];

		for (int i = 0; i < 10; i++) {
			dp[i][1] = 1;
		}

		long ret = 0;

		for (int i = 2; i <= n; i++) {
			ret = 0;

			for (int c = 0; c < 10; c++) {
				long possible = 0;

				for (int next : DIRECTION_MAP.get(c)) {
					possible = (possible + dp[next][(i-1)%2]) % MOD;
				}

				ret = (ret + possible) % MOD;
				dp[c][i%2] = (int)(possible % MOD);
			}
		}

		return (int)(ret % MOD);
	}

	public static void main(String[] args) {
		KnightDialer knightDialer = new KnightDialer();
		System.out.println(knightDialer.knightDialer(3131));
	}
}
