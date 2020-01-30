package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author neo82
 */
public class FourSum {
	public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);

		List<List<Integer>> ret = new ArrayList<>();

		for (int i = 0; i < nums.length - 3; i++) {
			if (i != 0 && nums[i-1] == nums[i]) {
				continue;
			}

			for (int j = i+1; j < nums.length - 2; j++) {
				if (j != i+1 && nums[j-1] == nums[j]) {
					continue;
				}

				int lo = j + 1;
				int hi = nums.length - 1;
				int found = target - (nums[i] + nums[j]);

				while (lo < hi) {
					if (found == (nums[lo] + nums[hi])) {
						ret.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));

						while (lo < hi && nums[lo] == nums[lo + 1]) {
							lo++;
						}

						while (lo < hi && nums[hi] == nums[hi - 1]) {
							hi--;
						}

						lo++;
						hi--;

					} else if ((nums[lo] + nums[hi]) < found) {
						lo++;
					} else {
						hi--;
					}
				}
			}
		}

		return ret;
	}

	public static void main(String[] args) {
		int [] nums = {0,0,0,0};
		int target = 0;

		FourSum fs = new FourSum();
		List<List<Integer>> res = fs.fourSum(nums, target);

		System.out.println(res);
	}
}
