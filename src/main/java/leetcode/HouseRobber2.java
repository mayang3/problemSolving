package leetcode;

/**
 * @author neo82
 */
public class HouseRobber2 {
	public int rob(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		} else if (nums.length == 1) {
			return nums[0];
		} else if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}

		return Math.max(solve(nums, 0, nums.length-2), solve(nums, 1, nums.length-1));
	}

	public int solve(int [] nums, int start, int end) {
		int [] dp = new int[nums.length];

		dp[start] = nums[start];
		dp[start+1] = Math.max(nums[start], nums[start+1]);

		for (int i = start+2; i < end+1; i++) {
			dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
		}

		return dp[end];
	}

	public static void main(String[] args) {
		int [] house = {1,2,3};

		HouseRobber2 hr2 = new HouseRobber2();
		System.out.println(hr2.rob(house));
	}
}
