package leetcode;

import java.util.*;

public class ThreeSum {
	// [-4, -1, -1, 0, 1, 2]
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);

		List<List<Integer>> resList = new ArrayList<>();

		for (int i = 0; i < nums.length-2; i++) {
			if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {

				int lo = i + 1;
				int hi = nums.length - 1;
				int target = -nums[i];

				while (lo < hi) {
					if (nums[lo] + nums[hi] == target) {
						resList.add(Arrays.asList(nums[i], nums[lo], nums[hi]));

						while (lo < hi && nums[lo] == nums[lo+1]) {
							lo++;
						}

						while (lo < hi && nums[hi] == nums[hi-1]) {
							hi--;
						}

						lo++;
						hi--;
					} else if (nums[lo] + nums[hi] < target) {
						lo++;
					} else {
						hi--;
					}
				}
			}
		}

		return resList;
	}

	public static void main(String[] args) {
		int [] nums = {-1,0,1,2,-1,-4};

		ThreeSum _ThreeSum = new ThreeSum();
		List<List<Integer>> lists = _ThreeSum.threeSum(nums);

		System.out.println(lists);
	}
}
