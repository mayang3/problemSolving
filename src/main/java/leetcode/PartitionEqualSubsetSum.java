package leetcode;

import java.util.Arrays;

public class PartitionEqualSubsetSum {
	public boolean canPartition(int[] nums) {
		int sum = 0;

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}

		if (sum % 2 != 0) {
			return false;
		}

		Boolean [][] dp = new Boolean[10001][200];

		return solve(dp, sum/2, nums, 0);
	}

	private boolean solve(Boolean [][] dp, int sum, int[] nums, int i) {
		if (sum == 0) {
			return true;
		} else if (i >= nums.length) {
			return false;
		}

		if (dp[sum][i] != null) {
			return dp[sum][i];
		}

		boolean res = false;

		// 현재 값을 넣는 경우
		if (sum >= nums[i]) {
			if (solve(dp, sum - nums[i], nums, i+1)) {
				res = true;
			}
		}

		// 현재 값을 넣지 않는 경우
		if (!res && solve(dp, sum, nums, i+1)) {
			res = true;
		}

		dp[sum][i] = res;

		return res;
	}

	public static void main(String[] args) {
		int [] nums = {1,4,4,7};

		PartitionEqualSubsetSum partitionEqualSubsetSum = new PartitionEqualSubsetSum();
		System.out.println(partitionEqualSubsetSum.canPartition(nums));
	}
}
