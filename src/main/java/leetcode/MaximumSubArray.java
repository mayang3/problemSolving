package leetcode;

public class MaximumSubArray {

	public int maxSubArray(int[] nums) {
		int sum = 0;
		int maxSoFar = Integer.MIN_VALUE;

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];

			maxSoFar = Math.max(sum, maxSoFar);

			if (sum < 0) {
				sum = 0;
			}
		}

		return maxSoFar;
	}

	public static void main(String[] args) {
		MaximumSubArray maximumSubArray = new MaximumSubArray();
		int result = maximumSubArray.maxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4});

		System.out.println(result);

	}
}
