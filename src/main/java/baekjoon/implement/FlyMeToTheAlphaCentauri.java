package baekjoon.implement;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FlyMeToTheAlphaCentauri {
	static int x;
	static int y;

	static Map<Integer, Integer>[] dp;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			x = scanner.nextInt();
			y = scanner.nextInt();

			dp = new HashMap[y+5];

			for (int i=0 ; i<dp.length ; i++) {
				dp[i] = new HashMap<>();
			}

			System.out.println(solve(x+1, 1) + 1);
		}
	}

	// cur 는 현재 광년의 위치
	static int solve(int cur, int offset) {
		if (cur == y-1) {
			return 1;
		}

		if (dp[cur].get(offset) != null) {
			return dp[cur].get(offset);
		}

		int min = 987654321;

		if (cur < y-1) {
			min = Math.min(min, 1 + solve(cur + offset + 1, offset + 1));
		}

		if (offset > 0 && cur < y-1) {
			min = Math.min(min, 1 + solve(cur + offset, offset));
		}

		if (offset > 1 && cur < y-1 ) {
			min = Math.min(min, 1 + solve(cur + offset - 1, offset - 1));
		}

		dp[cur].put(offset, min);

		return min;
	}
}
