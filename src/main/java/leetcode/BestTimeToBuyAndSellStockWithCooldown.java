package leetcode;

/**
 * @author neo82
 */
public class BestTimeToBuyAndSellStockWithCooldown {
	public static void main(String[] args) {
		int [] prices = {1,2,3,0,2};

		BestTimeToBuyAndSellStockWithCooldown cooldown = new BestTimeToBuyAndSellStockWithCooldown();
		int ret = cooldown.maxProfit(prices);

		System.out.println(ret);
	}

//	public int maxProfit(int[] prices) {
//		if(prices == null || prices.length <= 1) {
//			return 0;
//		}
//
//		// i 에서 최종 행동이 buy 로 끝나는 최대 profit 의 값
//		int [] buy = new int[prices.length];
//		// i 에서 최종 행동이 sell 로 끝나는 최대 profit 의 값
//		int [] sell = new int[prices.length];
//
//		// buy[0] 에서는 prices[0] 의 값만큼 물건을 산 행동이므로 -prices[0] 만큼의 profit 을 갖는다.
//		buy[0] = -prices[0];
//		// sell[0] 에서는 물건을 팔 수 없으므로 0 이다.
//		sell[0] = 0;
//
//		for(int i = 1; i < prices.length; i++) {
//			// buy[i] 에서는 아무 행동도 하지 않는 경우(cooldown), 와 현재 물건을 사는 경우가 있다.
//			// 이 중, 현재 물건을 사는 경우는 이전에 판매했던 마지막 행동의 profit 의 최대값에 현재 물건의 값 prices[i] 를 빼는 것이 될것이다. (buy 를 연속해서 두번 할 수는 없으므로...)
//			buy[i] = Math.max(buy[i-1], sell[Math.max(i-2, 0)] - prices[i]);
//			// sell 행동도 마찬가지로 아무 행동도 하지 않는 경우(sell[i-1] 값을 그대로 가져온다.) 와 현재 물건을 파는 행위가 있다.
//			// 현재 물건을 파는 행위는 이전에 구입 행위로 발생했던 최대 profit 의 값에 현재 물건의 값을 더한 값이 된다. (팔면 현재 물건의 값이 이득으로 들어오게 되는것이므로..)
//			sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i]);
//		}
//
//		return sell[prices.length-1];
//	}

	public int maxProfit(int [] prices) {
		if (prices == null || prices.length <= 1) {
			return 0;
		}

		int buyBeforeOneDay = -prices[0];
		int buyCurrent = 0;
		int sellCurrent = 0;
		int sellBeforeOneDay = 0;
		int sellBeforeTwoDay = 0;

		for (int i = 1; i < prices.length; i++) {
			buyCurrent = Math.max(buyBeforeOneDay, sellBeforeTwoDay - prices[i]);
			sellCurrent = Math.max(sellBeforeOneDay, buyBeforeOneDay + prices[i]);

			buyBeforeOneDay = buyCurrent;
			sellBeforeTwoDay = sellBeforeOneDay;
			sellBeforeOneDay = sellCurrent;
		}

		return sellCurrent;
	}
}
