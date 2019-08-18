package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author baejunbeom
 */
public class MaximumProductSubArray {

	public int maxProduct(int[] nums) {
		int min = nums[0];
		int max = nums[0];
		int ret = nums[0];

		for (int i = 1; i < nums.length; i++) {
			int temp = max;
			max = Math.max(Math.max(min * nums[i], max * nums[i]), nums[i]);
			min = Math.min(Math.min(min * nums[i], temp * nums[i]), nums[i]);

			ret = Math.max(ret, max);
		}

		return ret;
	}



	public static void main(String[] args) {

		MaximumProductSubArray maximumProductSubArray = new MaximumProductSubArray();
		int i = maximumProductSubArray.maxProduct(new int[] {2,3,-2,4});

		System.out.println(i);
	}
}
