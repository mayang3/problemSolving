package leetcode;

import java.util.Arrays;

public class MinimumDifferenceBetweenHighestAndLowestOfKScores {
	public int minimumDifference(int[] nums, int k) {
		if (k == 1) {
			return 0;
		}

		Arrays.sort(nums);

		int min = Integer.MAX_VALUE;

		for (int right = k-1; right < nums.length; right++) {
			int left = right - k + 1;

			min = Math.min(min, nums[right] - nums[left]);
		}

		return min;
	}

	public static void main(String[] args) {
		int [] nums = {9,4,1,7};
		int k = 2;

		MinimumDifferenceBetweenHighestAndLowestOfKScores minimumDifferenceBetweenHighestAndLowestOfKScores = new MinimumDifferenceBetweenHighestAndLowestOfKScores();
		System.out.println(minimumDifferenceBetweenHighestAndLowestOfKScores.minimumDifference(nums, k));
	}
}
