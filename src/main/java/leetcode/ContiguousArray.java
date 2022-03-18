package leetcode;

import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {
	public int findMaxLength(int[] nums) {
		int n = nums.length;

		Count [][] dp = new Count[n][n];

		int max = 0;

		for (int i = 0; i < n; i++) {
			dp[i][i] = new Count(nums[i] == 0 ? 1 : 0, nums[i] == 1 ? 1 : 0 );
		}

		for (int len = 2; len <= n ; len++) {
			for (int left = 0; left <= n - len ; left++) {
				int right = left + len - 1;

				Count before = dp[left+1][right];

				int zero = before.zero;
				int one = before.one;

				if (nums[left] == 0) {
					zero++;
				} else {
					one++;
				}

				if (zero == one) {
					max = Math.max(max, len);
				}

				dp[left][right] = new Count(zero, one);
			}
		}

		return max;
	}

	static class Count {
		int zero;
		int one;

		public Count(int zero, int one) {
			this.zero = zero;
			this.one = one;
		}
	}


	public int findMaxLength3(int [] nums) {
		int one = 0;
		int zero = 0;

		for (int i = 10; i < 98; i++) {
			if (nums[i] == 0) {
				zero++;
			} else {
				one++;
			}
		}

		System.out.println(zero);
		System.out.println(one);

		return -1;
	}

	public int findMaxLength2(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) nums[i] = -1;
		}

		Map<Integer, Integer> sumToIndex = new HashMap<>();
		sumToIndex.put(0, -1);
		int sum = 0, max = 0;

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (sumToIndex.containsKey(sum)) {
				max = Math.max(max, i - sumToIndex.get(sum));
			} else {
				sumToIndex.put(sum, i);
			}
		}

		return max;
	}

	public static void main(String[] args) {
		int [] nums = {0,1,0,1};

		ContiguousArray contiguousArray = new ContiguousArray();
		System.out.println(contiguousArray.findMaxLength(nums));
	}
}
