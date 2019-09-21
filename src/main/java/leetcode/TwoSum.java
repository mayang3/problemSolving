package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author neo82
 */
public class TwoSum {

	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> numMap = new HashMap<>();

		int [] ret = new int[2];

		for (int i = 0; i < nums.length; i++) {
			if (numMap.containsKey(target - nums[i])) {

				int min = i;
				int max = numMap.get(target - nums[i]);

				if (min > max) {
					int temp = min;
					min = max;
					max = temp;
				}

				ret[0] = min;
				ret[1] = max;

				return ret;
			} else {
				numMap.put(nums[i], i);
			}
		}

		return null;
	}

	public static void main(String[] args) {
		int [] nums = {2,7,11,15};

		TwoSum twoSum = new TwoSum();
		int[] ret = twoSum.twoSum(nums, 9);

		System.out.println(Arrays.toString(ret));
	}
}
