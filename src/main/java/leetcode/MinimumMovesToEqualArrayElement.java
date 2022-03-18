package leetcode;

import java.util.Arrays;

public class MinimumMovesToEqualArrayElement {
	public int minMoves(int[] nums) {
		Arrays.sort(nums);

		int [] nums2 = new int[3];

		nums2[2] = nums[nums.length-1];

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < nums.length - 1; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}

		nums2[0] = min;
		nums2[1] = max;

		int count = 0;

		while (isAllSame(nums2) == false) {
			for (int i = 0; i < nums2.length - 1; i++) {
				nums2[i]++;
			}

			Arrays.sort(nums2);

			count++;
		}

		return count;
	}

	private boolean isAllSame(int[] nums2) {
		for (int i = 1; i < nums2.length; i++) {
			if (nums2[i-1] != nums2[i]) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		int [] nums = {1,5,2,3,4,2,5,8,7,6};

		MinimumMovesToEqualArrayElement minimumMovesToEqualArrayElement = new MinimumMovesToEqualArrayElement();
		System.out.println(minimumMovesToEqualArrayElement.minMoves(nums));
	}
}
