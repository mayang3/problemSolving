package leetcode;

public class BestTimeToBuyAndSellStock2 {
	public int maxProfit(int[] prices) {
		int n = prices.length;

		int [] hold = new int[n];
		int [] noHold = new int[n];

		hold[0] = -prices[0];
		noHold[0] = 0;

		for (int i = 1; i < n; i++) {
			// 1. 현재 위치에서 주식을 보유하는 경우의 수
			// 1-1. 현재 보유한 주식을 팔고 현재 위치에서 다시 산다.
			// 1-2. 현재 보유한 주식을 그냥 두고 아무 행동도 하지 않는다.
			// 1-3. 이전에 주식을 팔았던 상태에서 새로 주식을 매입한다.
			hold[i] = Math.max(hold[i-1], noHold[i-1] - prices[i]);

			// 2. 현재 위치에서 주식을 보유하지 않는 경우의 수
			// 2-1. 이전에 보유했던 주식을 현재 위치에서 팔고, 추가로 현재 위치에서 주식을 다시 사지 않는다.
			// 2-2. 이전에 주식을 보유하지 않았던 상태에서, 현재 위치에서도 주식을 매입하지 않는다.
			noHold[i] = Math.max(hold[i-1] + prices[i], noHold[i-1]);
		}

		return noHold[n-1];
	}

	public static void main(String[] args) {
		int [] prices = {7,6,4,3,1};

		BestTimeToBuyAndSellStock2 bestTimeToBuyAndSellStock2 = new BestTimeToBuyAndSellStock2();
		System.out.println(bestTimeToBuyAndSellStock2.maxProfit(prices));
	}
}
