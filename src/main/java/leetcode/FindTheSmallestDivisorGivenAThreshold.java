package leetcode;

public class FindTheSmallestDivisorGivenAThreshold {

	public static void main(String[] args) {
		int [] nums = {1,2,3};
		int threshold = 6;

		FindTheSmallestDivisorGivenAThreshold ftsdgat = new FindTheSmallestDivisorGivenAThreshold();
		System.out.println(ftsdgat.smallestDivisor(nums, threshold));
	}

	public int smallestDivisor(int[] nums, int threshold) {
		return solve(nums, threshold, 0, (int)1e6);
	}

	int solve(int [] nums, int threshold, int left, int right) {
		if (left < right) {
			// medium divisor
			int m = (left + right) / 2;

			int sum1 = getSum(nums, m);
			int sum2 = getSum(nums, m+1);

			if (sum1 > threshold && sum2 <= threshold) {
				return m+1;
			}

			if (sum1 <= threshold) {
				return solve(nums, threshold, left, m);
			} else {
				return solve(nums, threshold, m+1, right);
			}
		}

		return 0;
	}

	int getSum(int [] nums, int divisor) {
		int sum = 0;

		for (int i = 0; i < nums.length; i++) {
			sum += Math.ceil((double)nums[i] / (double)divisor);
		}

		return sum;
	}
}
