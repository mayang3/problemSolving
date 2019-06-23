package leetcode;

import java.util.Arrays;

/**
 * @author neo82
 */
public class ShortestUnsortedContinuousSubarray {
	public int findUnsortedSubarray(int[] nums) {

		int [] original = Arrays.copyOf(nums, nums.length);

		int start = 0;
		int end = 0;

		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i++) {
			if (original[i] != nums[i]) {
				start = i;
				break;
			}
		}

		for (int i = nums.length - 1; i >= 0; i--) {
			if (original[i] != nums[i]) {
				end = i;
				break;
			}
		}

		return start == end ? 0 : end - start + 1;
	}

	public static void main(String[] args) {
		ShortestUnsortedContinuousSubarray subarray = new ShortestUnsortedContinuousSubarray();

		int [] nums = {2, 6, 4, 8, 10, 9, 15};

		int unsortedSubarray = subarray.findUnsortedSubarray(nums);

		System.out.println(unsortedSubarray);
	}
}
