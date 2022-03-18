package leetcode;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumsDivisibleByK {

	public int bruteForce(int [] nums, int k) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			int sum = 0;
			for (int j = i; j < nums.length; j++) {
				sum += nums[j];

				if (sum % k == 0) {
					count++;
				}
			}
		}

		return count;
	}

//	public int subarraysDivByK(int[] A, int K) {
//		Map<Integer, Integer> map = new HashMap<>();
//		map.put(0, 1);
//		int count = 0, sum = 0;
//		for(int a : A) {
//			sum = (sum + a) % K;
//			if(sum < 0) sum += K;  // Because -1 % 5 = -1, but we need the positive mod 4
//			count += map.getOrDefault(sum, 0);
//			map.put(sum, map.getOrDefault(sum, 0) + 1);
//		}
//		return count;
//	}

	// My Solution
	public int subarraysDivByK(int[] nums, int k) {
		long prefixSum = 0;

		Map<Integer, Integer> countMap = new HashMap<>();

		int count = 0;

		for (int i = 0; i < nums.length; i++) {
			prefixSum += nums[i];

			int dividedVal = (int)(prefixSum % k);

			if (dividedVal >= 0 && countMap.containsKey(dividedVal - k)) {
				count += countMap.get(dividedVal - k);
			}

			if (dividedVal < 0 && countMap.containsKey(dividedVal + k)) {
				count += countMap.get(dividedVal + k);
			}

			// x - dividedVal = 0;
			if (countMap.containsKey((dividedVal))) {
				count += countMap.get((dividedVal));
			}

			if (dividedVal == 0) {
				count++;
			}

			countMap.merge(dividedVal, 1, Integer::sum);
		}

		return count;
	}

	public static void main(String[] args) {
		int [] nums = {4,2,5,-14,23};
		int k = 2;

		int [] prefixSum = new int[nums.length];

		prefixSum[0] = nums[0];

		for (int i = 1; i < nums.length; i++) {
			prefixSum[i] = prefixSum[i-1] + nums[i];
		}

		for (int i = 0; i < nums.length; i++) {
			prefixSum[i] %= 2;
		}

		SubarraySumsDivisibleByK subarraySumsDivisibleByK = new SubarraySumsDivisibleByK();
		System.out.println(subarraySumsDivisibleByK.subarraysDivByK(nums, k));
		System.out.println(subarraySumsDivisibleByK.bruteForce(nums, k));
	}
}
