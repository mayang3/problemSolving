package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author neo82
 */
public class LongestConsecutiveSequence {
	public int longestConsecutive(int[] nums) {
		Set<Integer> set = new HashSet<>();

		for (int num : nums) {
			set.add(num);
		}

		int ans = 0;

		for (int i = 0; i < nums.length; i++) {

			// starting point
			if (set.contains(nums[i] - 1) == false) {
				int j = nums[i];

				while (set.contains(j)) {
					j++;
				}

				ans = Math.max(ans, j-nums[i]);
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		LongestConsecutiveSequence sequence = new LongestConsecutiveSequence();

		int [] nums = {100, 4, 200, 1, 3, 2};

		System.out.println(sequence.longestConsecutive(nums));
	}
}
