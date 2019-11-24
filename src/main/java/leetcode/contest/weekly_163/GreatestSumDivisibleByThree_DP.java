package leetcode.contest.weekly_163;

import java.util.Arrays;

public class GreatestSumDivisibleByThree_DP {

	public static void main(String[] args) {
		int [] nums = {3,6,5,1,8};

		GreatestSumDivisibleByThree_DP greatestSumDivisibleByThree_dp = new GreatestSumDivisibleByThree_DP();
		int res = greatestSumDivisibleByThree_dp.maxSumDivK(nums, 3);

		System.out.println(res);
	}

	public int maxSumDivThree(int[] nums) {
		return maxSumDivK(nums, 3);
	}
	public int maxSumDivK(int[] nums, int k){
		if (k == 0) {
			return -1;
		}

		int [] dp = new int[k];

		for (int num : nums) {
			int [] beforeDp = Arrays.copyOf(dp, k);
			for (int i = 0; i < k; i++) {
				dp[(num + beforeDp[i]) % k] = Math.max(dp[(num + beforeDp[i]) % k], num + beforeDp[i]);
			}
		}

		return dp[0];
	}
}
