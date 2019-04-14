package leetcode;

/*

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.


 */

/**
 * @author baejunbeom
 */
public class BestTimeToBuyAndSellStock {

	/**
	 * Time Complexity : O(N)
	 * Space Complexity : O(1)
	 *
	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {

		if (prices.length == 0) {
			return 0;
		}

		int buy = prices[0];
		int diff = 0;

		for (int i = 1 ; i<prices.length ; i++) {

			if (buy > prices[i]) {
				buy = prices[i];
			} else {
				diff = Math.max(prices[i] - buy, diff);
			}
		}

		return diff;
	}

	public static void main(String[] args) {
		BestTimeToBuyAndSellStock bestTimeToBuyAndSellStock = new BestTimeToBuyAndSellStock();
		int i = bestTimeToBuyAndSellStock.maxProfit(new int[] {4, 1, 2});

		System.out.println(i);
	}
}
