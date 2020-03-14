package leetcode;

public class CoinChange {
	public int coinChange(int[] coins, int amount) {
		Integer [][] dp = new Integer[coins.length+1][amount+1];

		int res = solve(dp, coins, amount, 0);

		return res == Integer.MAX_VALUE ? -1 : res;
	}

	private int solve(Integer[][] dp, int[] coins, int amount, int currentIndex) {
		if (amount == 0) {
			return 0;
		} else if (amount < 0 || currentIndex >= coins.length) {
			return Integer.MAX_VALUE;
		}

		if (dp[currentIndex][amount] != null) {
			return dp[currentIndex][amount];
		}

		int count = Integer.MAX_VALUE;

		// 현재 동전으로 교환하는 경우
		if (amount - coins[currentIndex] >= 0) {
			int res = solve(dp, coins, amount - coins[currentIndex], currentIndex);

			// 최종적으로 교환할 수 없는 경우는 동전의 카운트를 세면 안된다.
			if (res != Integer.MAX_VALUE) {
				count = res + 1;
			}
		}

		// 현재 동전으로 교환하지 않는 경우
		count = Math.min(count, solve(dp, coins, amount, currentIndex+1));

		return dp[currentIndex][amount] = count;
	}

	public static void main(String[] args) {
		int [] coins = {1,2,5};
		int amount = 11;

		CoinChange coinChange = new CoinChange();
		int res = coinChange.coinChange(coins, amount);

		System.out.println(res);
	}
}
