package leetcode;

import java.util.*;

public class DeleteAndEarn {
	public static void main(String[] args) {
		DeleteAndEarn deleteAndEarn = new DeleteAndEarn();

		int [] nums = {4,10,10,8,1,4,10,9,7,6};

		System.out.println(deleteAndEarn.deleteAndEarn(nums));
	}

	public int deleteAndEarn(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		int maxNum = 0;

		for (int i = 0; i < nums.length; i++) {
			map.merge(nums[i], 1, Integer::sum);
			maxNum = Math.max(maxNum, nums[i]);
		}

		int max = 0;
		int [] dp = new int[maxNum+1];

		dp[1] = max = 1 * map.getOrDefault(1, 0);

		if (maxNum > 1) {
			dp[2] = max = Math.max(dp[1], 2 * map.getOrDefault(2, 0));

			for (int i = 3; i <= maxNum; i++) {
				dp[i] = Math.max(dp[i - 2] + i * map.getOrDefault(i, 0), dp[i - 1]);
				max = Math.max(max, dp[i]);
			}
		}

		return max;
	}


}
