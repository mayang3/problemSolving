package leetcode;

public class LongestIncreasingSubsequence {
	public int lengthOfLIS(int[] nums) {
//		Integer [][] dp = new Integer[nums.length][nums.length];
//
//		return solve(dp, nums, 0, -1);

		return findLISLength(nums);
	}

	private int solve(Integer[][] dp, int[] nums, int currentIndex, int previousIndex) {
		if (currentIndex == nums.length) {
			return 0;
		}

		if (dp[currentIndex][previousIndex+1] != null) {
			return dp[currentIndex][previousIndex+1];
		}

		int c1 = 0;

		if (previousIndex == -1 || nums[currentIndex] > nums[previousIndex]) {
			c1 = 1 + solve(dp, nums, currentIndex + 1, currentIndex);
		}

		int c2 = solve(dp, nums, currentIndex + 1, previousIndex);

		return dp[currentIndex][previousIndex+1] = Math.max(c1, c2);
	}

	public int findLISLength(int[] nums) {
		int[] dp = new int[nums.length];
		dp[0] = 1;

		int maxLength = 1;
		for (int i=1; i<nums.length; i++) {
			dp[i] = 1;
			for (int j=0; j<i; j++) {

				if (nums[i] > nums[j] && dp[i] <= dp[j]) {
					dp[i] = dp[j] + 1;
					maxLength = Math.max(maxLength, dp[i]);
				}
			}
		}
		return maxLength;
	}

	public static void main(String[] args) {
		int [] nums = {10,9,2,5,3,7,101,18};

		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
		System.out.println(lis.lengthOfLIS(nums));
	}
}
