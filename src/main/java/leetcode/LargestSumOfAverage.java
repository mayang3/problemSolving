package leetcode;

public class LargestSumOfAverage {
	public double largestSumOfAverages(int[] nums, int k) {
		Double [][] dp = new Double[nums.length][k+1];
		return solve(dp, nums, k, 0);
	}

	private double solve(Double [][] dp, int[] nums, int k, int i) {
		if ((k == 0 && i < nums.length) || (k > 0 && i >= nums.length)) {
			return -Double.MAX_VALUE;
		} else if (i >= nums.length) {
			return 0D;
		}

		if (dp[i][k] != null) {
			return dp[i][k];
		}

		int pSum = 0;
		double max = 0;

		for (int j = i; j < nums.length; j++) {
			double window = j - i + 1;
			pSum += nums[j];
			max = Math.max(max, (double)pSum / window + solve(dp, nums, k-1, j+1));
		}

		return dp[i][k] = max;
	}

	public static void main(String[] args) {
		int [] nums = {4,1,7,5,6,2,3};
		int k = 4;

		LargestSumOfAverage largestSumOfAverage = new LargestSumOfAverage();
		System.out.println(largestSumOfAverage.largestSumOfAverages(nums, k));
	}
}
