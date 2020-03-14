package leetcode;

public class CoinChange_BottomUp {
	public int coinChange(int[] coins, int amount) {
		// amount 같은 경우에는 0 인 case 가 있으므로 각각 length 를 1씩 더해준다.
		int [][] dp = new int[coins.length][amount+1];

		for (int y = 0; y < coins.length; y++) {
			for (int x = 0; x <= amount ; x++) {
				dp[y][x] = Integer.MAX_VALUE;
			}
		}

		// amount == 0 인 경우, 어떤 종류의 동전도 필요하지 않다.
		for (int y = 0; y < coins.length; y++) {
			dp[y][0] = 0;
		}

		for (int y = 0; y < coins.length; y++) {
			for (int x = 1; x <= amount; x++) {
				if (y > 0) {
					dp[y][x] = dp[y-1][x];
				}

				if (x >= coins[y]) {
					if (dp[y][x-coins[y]] != Integer.MAX_VALUE) {
						dp[y][x] = Math.min(dp[y][x], dp[y][x-coins[y]] + 1);
					}
				}

			}
		}

		return dp[coins.length-1][amount] == Integer.MAX_VALUE ? -1 : dp[coins.length-1][amount];
	}

	public static void main(String[] args) {
		int [] coins = {1,2,5};
		int amount = 11;

		CoinChange_BottomUp ccm = new CoinChange_BottomUp();

		int res = ccm.coinChange(coins, amount);

		System.out.println(res);
	}
}
