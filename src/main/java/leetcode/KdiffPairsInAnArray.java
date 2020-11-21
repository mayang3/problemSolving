package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class KdiffPairsInAnArray {
	public int findPairs(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			map.merge(nums[i], 1, Integer::sum);
		}

		int count = 0;

		Arrays.sort(nums);

		for (int i = 0; i< nums.length; i++) {
			map.merge(nums[i], -1, Integer::sum);

			if (map.get(nums[i]) == 0) {
				map.remove(nums[i]);
			}

			if (i != 0 && nums[i-1] == nums[i]) {
				continue;
			}

			if (map.containsKey(nums[i] + k) || map.containsKey(nums[i] - k)) {
				count++;
			}
		}

		return count;
	}

	public static void main(String[] args) {
		int [] nums = {1,2,3,4,5};
		int k = 1;

		KdiffPairsInAnArray kdiffPairsInAnArray = new KdiffPairsInAnArray();

		System.out.println(kdiffPairsInAnArray.findPairs(nums, k));
	}
}
