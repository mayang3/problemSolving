package leetcode;

import java.util.*;

public class MaximumSumOf3NonOverlappingSubArrays {
	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {

		int max = Integer.MIN_VALUE;
		int [] res = new int[3];

		MaxSolve maxSolve = new MaxSolve(max,res);

		for (int i = 0; i < nums.length; i++) {
			maxSolve.set(max,res);
			maxSolve.solve(nums, new int[3], k, 0, i);
			max = maxSolve.max;
			res = maxSolve.res;
		}


		return maxSolve.res;
	}

	static class MaxSolve {
		int max;
		int [] res;

		public MaxSolve(int max, int [] res) {
			this.max = max;
			this.res = res;
		}

		void set(int max, int [] res) {
			this.max = max;
			this.res = res;
		}

		void solve(int[] nums, int [] cur, int k, int cnt, int currentIndex) {
			if (cnt == 0) {
				int sum = getSum(cur);

				if (sum > max || (sum == max && isSmallLexicographically(cur))) {
					res = cur;
					max = sum;
				}
			} else if (currentIndex > nums.length) {
				return;
			}

			// 현재를 넣는 경우
			if (currentIndex+k < nums.length) {
				solve(nums, cur, k, cnt - 1, currentIndex + k);
			}

			// 현재를 넣지 않는 경우

		}

		private boolean isSmallLexicographically(int[] cur) {
			return false;
		}

		private int getSum(int[] res) {
			int sum = 0;

			for (int i = 0; i < 3; i++) {
				sum += res[i];
			}

			return sum;
		}
	}

	private void solve(int [] res, int[] nums, int k, int cnt, int max, int currentIndex) {
		if (cnt == 0 && isMax(res, max)) {

		}

		return;
	}

	private boolean isMax(int[] res, int max) {
		return false;
	}

	public static void main(String[] args) {
		int [] nums = {1,2,1,2,6,7,5,1};
		int k = 2;

		MaximumSumOf3NonOverlappingSubArrays arrays = new MaximumSumOf3NonOverlappingSubArrays();
		int[] ints = arrays.maxSumOfThreeSubarrays(nums, k);

		System.out.println(Arrays.toString(ints));
	}
}
