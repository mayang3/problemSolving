package leetcode;

/**
 * @author neo82
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

		if (prices == null || prices.length == 0) {
			return 0;
		}

		int buy = prices[0];
		int max = 0;

		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < buy) {
				buy = prices[i];
			} else {
				max = Math.max(max, prices[i] - buy);
			}
		}

		return max;
	}

	public static void main(String[] args) {
		BestTimeToBuyAndSellStock bestTimeToBuyAndSellStock = new BestTimeToBuyAndSellStock();
		int i = bestTimeToBuyAndSellStock.maxProfit(new int[] {7,1,5,3,6,4});

		System.out.println(i);
	}
}
