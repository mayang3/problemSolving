package leetcode;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
	public int subarraySum(int[] nums, int k) {
		int sum = 0;
		int ret = 0;

		Map<Integer, Integer> prefixSumMap = new HashMap<>();
		prefixSumMap.put(0,1);

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];

			if (prefixSumMap.containsKey(sum - k)) {
				ret += prefixSumMap.get(sum - k);
			}

			prefixSumMap.put(sum, prefixSumMap.getOrDefault(sum,0) + 1);
		}

		return ret;
	}

	public static void main(String[] args) {
		int [] nums = {1,1,1};
		int k = 2;

		SubarraySumEqualsK subarraySumEqualsK = new SubarraySumEqualsK();
		int res = subarraySumEqualsK.subarraySum(nums, k);

		System.out.println(res);
	}
}
