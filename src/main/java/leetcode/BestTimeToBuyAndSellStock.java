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
		int min = Integer.MAX_VALUE;
		int max = 0;

		for (int i=0; i<prices.length; i++) {
			int price = prices[i];

			if (price - min > 0) {
				max = Math.max(max, price-min);
			}

			min = Math.min(min, price);
		}

		return max;
	}

	public static void main(String[] args) {
		BestTimeToBuyAndSellStock bestTimeToBuyAndSellStock = new BestTimeToBuyAndSellStock();
		int i = bestTimeToBuyAndSellStock.maxProfit(new int[] {7,1,5,3,6,4});

		System.out.println(i);
	}
}
