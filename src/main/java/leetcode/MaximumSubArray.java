package leetcode;

/*

Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

 */

/**
 * @author baejunbeom
 */
public class MaximumSubArray {

	public int maxSubArray(int[] nums) {

		if (nums.length == 1) {
			return nums[0];
		}

		int max = 0;
		int result = Integer.MIN_VALUE;

		for (int i=0 ;i<nums.length ; i++) {
			max = Math.max(nums[i] + max, nums[i]);
			result = Math.max(result, max);
		}

		return result;
	}

	public static void main(String[] args) {
		MaximumSubArray maximumSubArray = new MaximumSubArray();
		int result = maximumSubArray.maxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4});

		System.out.println(result);

	}
}
