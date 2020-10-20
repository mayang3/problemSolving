package leetcode;

public class CoinChange2 {
	public int change(int amount, int[] coins) {
		Integer [][] dp = new Integer[coins.length][amount+1];

		return bottomUp(amount, coins);
//		return solve(dp, amount, coins, 0);
	}

	private int bottomUp(int amount, int [] coins) {
		if (amount == 0) {
			return 1;
		}

		int n = coins.length;
		int [][] dp = new int[n][amount+1];

		// amount 가 0인 경우는, 그 자체로 0을 만든거라고 볼 수 있기 때문에 카운트가 1이다.
		for (int i = 0; i < n; i++) {
			dp[i][0] = 1;
		}


		for (int i = 0; i < n; i++) {
			for (int total = 1; total <= amount ; total++) {
				// 현재 동전을 넣지 않는 경우
				if (i > 0) {
					dp[i][total] = dp[i-1][total];
				}

				// 현재 동전을 넣는 경우
				if (total >= coins[i]) {
					dp[i][total] += dp[i][total-coins[i]];
				}
			}
		}

		return dp[n-1][amount];
	}

	private int solve(Integer[][] dp, int amount, int[] coins, int i) {
		if (amount == 0) {
			return 1;
		} else if (i >= coins.length) {
			return 0;
		}

		if (dp[i][amount] != null) {
			return dp[i][amount];
		}

		int count = 0;

		// 현재 coin 을 넣는 경우
		if (amount - coins[i] >= 0) {
			count += solve(dp, amount-coins[i], coins, i);
		}

		// 현재 coin 을 넣지 않는 경우
		count += solve(dp, amount, coins, i+1);

		return dp[i][amount] = count;
	}

	public static void main(String[] args) {
		int amount = 5;
		int [] coins = {1, 2, 5};

		CoinChange2 coinChange2 = new CoinChange2();

		int res = coinChange2.change(amount, coins);

		System.out.println(res);
	}
}
