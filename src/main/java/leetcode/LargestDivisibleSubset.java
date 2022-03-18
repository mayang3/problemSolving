package leetcode;

import java.util.*;

public class LargestDivisibleSubset {
	public List<Integer> largestDivisibleSubset(int[] nums) {
		int n = nums.length;

		List<Integer> [] dp = new List[n];

		for (int i = 0; i < n; i++) {
			dp[i] = new ArrayList<>();
		}

		Arrays.sort(nums);

		for (int i = 0; i < n; i++) {
			List<Integer> newList = new ArrayList<>();

			for (int j = 0; j < i; j++) {
				if (nums[i] % nums[j] == 0 && dp[j].size() > newList.size()) {
					newList = dp[j];
				}
			}

			dp[i].addAll(newList);
			dp[i].add(nums[i]);
		}

		List<Integer> res = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			if (res.size() < dp[i].size()) {
				res = dp[i];
			}
		}

		return res;
	}

	public static void main(String[] args) {
		int [] nums = {1,2,4,8};

		LargestDivisibleSubset largestDivisibleSubset = new LargestDivisibleSubset();
		System.out.println(largestDivisibleSubset.largestDivisibleSubset(nums));
	}
}
