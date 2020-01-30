package leetcode;

import java.util.Arrays;

public class ThreeSumClosest {
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);

		int min = Integer.MAX_VALUE;
		int ret = 0;

		for (int i = 0; i < nums.length - 2; i++) {
			int j = i+1;
			int k = nums.length - 1;
			int sum = target-nums[i];

			while (j < k) {
				int abs = Math.abs(target - (nums[i] + nums[k] + nums[j]));

				if (abs < min) {
					min = abs;
					ret = nums[i] + nums[k] + nums[j];
				}

				if (nums[j] + nums[k] < sum) {
					j++;
				} else {
					k--;
				}
			}
		}

		return ret;
	}

	public static void main(String[] args) {
		int [] nums = {0,2,1,-3};
		int target = 1;

		ThreeSumClosest threeSumClosest = new ThreeSumClosest();
		int res = threeSumClosest.threeSumClosest(nums, target);

		System.out.println(res);
	}
}
