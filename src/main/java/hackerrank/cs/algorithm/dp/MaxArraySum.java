package hackerrank.cs.algorithm.dp;

import java.util.*;

/**
 */
public class MaxArraySum {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		long [] arr = new long[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextLong();
		}

		DP [] dp = new DP[n];

		dp[0] = new DP(arr[0], arr[0]);
		dp[1] = new DP(Math.max(arr[0], arr[1]), arr[1]);

		long ret = Long.MIN_VALUE;

		for (int i = 2; i < n; i++) {
			// 현재 위치의 최대값은 아래 세가지 조건을 비교하면 된다.
			// 1. 그냥 자기 자신
			// 2. i-2 번째 가 가지고 있는 최대값 + 자기 자신 (maxHere)
			// 3. 처음 ~ i-2 번째까지 가지고 있는 최대값 (maxSoFar)
			long max = Math.max(arr[i] + dp[i-2].maxSoFar, Math.max(arr[i], arr[i] + dp[i-2].maxHere));

			// 그리고 maxSoFar 의 갱신은 바로 이전의 최대값과 비교해주어야 한다.
			dp[i] = new DP(Math.max(max, dp[i-1].maxSoFar), max);

			ret = Math.max(ret, max);
		}

		System.out.println(ret);
	}

	static class DP {
		long maxSoFar;
		long maxHere;

		DP(long maxSoFar, long maxHere) {
			this.maxSoFar = maxSoFar;
			this.maxHere = maxHere;
		}
	}

}
