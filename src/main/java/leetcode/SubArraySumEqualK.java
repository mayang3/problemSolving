package leetcode;

public class SubArraySumEqualK {
	public int subarraySum(int[] nums, int k) {

		int ret = 0;
		int sum = 0;
		int left = 0;

		for (int right = 0; right < nums.length; right++) {
			sum += nums[right];

			if (sum == k) {
				ret++;
			}

			while (sum > k && left < right) {
				sum -= nums[left++];

				if (sum == k) {
					ret++;
				}
			}
		}

		return ret;
	}

	public static void main(String[] args) {
		int [] nums = {1,2,3};
		int k = 3;

		SubArraySumEqualK subArraySumEqualK = new SubArraySumEqualK();
		System.out.println(subArraySumEqualK.subarraySum(nums, k));
	}
}
