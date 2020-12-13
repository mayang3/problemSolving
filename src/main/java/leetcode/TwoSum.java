package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author neo82
 */
public class TwoSum {

	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			int remains = target - nums[i];

			if (map.containsKey(remains)) {
				return new int[] {map.get(remains), i};
			} else {
				map.put(nums[i], i);
			}
		}

		return new int[2];
	}

	public static void main(String[] args) {
		int [] nums = {2,7,11,15};

		TwoSum twoSum = new TwoSum();
		int[] ret = twoSum.twoSum(nums, 9);

		System.out.println(Arrays.toString(ret));
	}
}
