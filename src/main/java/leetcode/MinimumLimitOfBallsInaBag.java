package leetcode;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class MinimumLimitOfBallsInaBag {
	public int minimumSize(int[] nums, int maxOperations) {
		int left = 1;
		int right = (int)1e9;

		while (left < right) {
			int mid = (left + right) / 2;

			if (isPossibleInMaxOp(nums, mid, maxOperations)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}

		return left;
	}

	// O(n) 만 되면 가능하다.
	private boolean isPossibleInMaxOp(int[] nums, int bagSize, int maxOperations) {
		int op = 0;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > bagSize) {
				op += Math.ceil((double)nums[i] / (double)bagSize) - 1;
			}
		}

		return op <= maxOperations;
	}

	public static void main(String[] args) {
		int [] nums = {9};
		int maxOperations = 3;

		MinimumLimitOfBallsInaBag minimumLimitOfBallsInaBag = new MinimumLimitOfBallsInaBag();
		System.out.println(minimumLimitOfBallsInaBag.minimumSize(nums, maxOperations));
	}
}
