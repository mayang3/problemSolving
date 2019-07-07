package leetcode;

/**
 * @author neo82
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {
	public static void main(String[] args) {
		int [] prices = {1,3,2,8,4,9};
		int fee = 2;

		BestTimeToBuyAndSellStockWithTransactionFee transactionFee = new BestTimeToBuyAndSellStockWithTransactionFee();

		int ret = transactionFee.maxProfit(prices, fee);

		System.out.println(ret);
	}

	public int maxProfit(int [] prices, int fee) {
		if (prices == null || prices.length <= 1) {
			return 0;
		}

		int [] buy = new int[prices.length];
		int [] sell = new int[prices.length];

		buy[0] = -prices[0];
		sell[0] = 0;

		for (int i = 1; i < prices.length; i++) {
			buy[i] = Math.max(buy[i-1], sell[i-1] - prices[i]);
			sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i] - fee);
		}

		return sell[prices.length-1];
	}
}
