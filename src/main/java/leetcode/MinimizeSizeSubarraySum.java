package leetcode;

import java.util.Arrays;

public class MinimizeSizeSubarraySum {
	public int minSubArrayLen(int target, int[] nums) {

		Arrays.sort(nums);

		int sum = 0;

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];

			if (sum >= target) {
				return i+1;
			}
		}

		return 0;
//		int n = nums.length;
//		int sum = 0;
//		int slow = 0;
//		int min = Integer.MAX_VALUE;
//
//		for (int fast = 0; fast < n; fast++) {
//			sum += nums[fast];
//
//			while (sum >= target && slow <= fast) {
//				min = Math.min(min, fast - slow + 1);
//				sum -= nums[slow];
//				slow++;
//			}
//		}
//
//		return min == Integer.MAX_VALUE ? 0 : min;
	}

	public static void main(String[] args) {
		int target = 7;
		int [] nums = {2,3,1,2,4,3};

		MinimizeSizeSubarraySum minimizeSizeSubarraySum = new MinimizeSizeSubarraySum();
		System.out.println(minimizeSizeSubarraySum.minSubArrayLen(target, nums));

	}
}
