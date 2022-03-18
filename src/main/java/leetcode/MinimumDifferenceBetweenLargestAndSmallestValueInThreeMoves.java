package leetcode;

import java.util.Arrays;
import java.util.TreeMap;

public class MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {
	public int minDifference(int[] nums) {
		int n = nums.length;
		int lastIndex = Math.max(1, n-3);

		Arrays.sort(nums);

		TreeMap<Integer, Integer> treeMap = new TreeMap<>();

		for (int i = 0; i < lastIndex; i++) {
			treeMap.merge(nums[i], 1, Integer::sum);
		}

		int min = treeMap.lastKey() - treeMap.firstKey();

		for (int left = 0; left < 3 && lastIndex < n; left++) {
			treeMap.merge(nums[left], -1, Integer::sum);
			removeIfZero(treeMap, nums[left]);
			treeMap.merge(nums[lastIndex], 1, Integer::sum);

			min = Math.min(min, treeMap.lastKey() - treeMap.firstKey());

			lastIndex++;
		}

		return min;
	}

	private void removeIfZero(TreeMap<Integer, Integer> treeMap, int index) {
		if (treeMap.get(index) <= 0) {
			treeMap.remove(index);
		}
	}

	public static void main(String[] args) {
		int [] nums = {1,3,8};

		MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves minimumDifferenceBetweenLargestAndSmallestValueInThreeMoves = new MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves();
		System.out.println(minimumDifferenceBetweenLargestAndSmallestValueInThreeMoves.minDifference(nums));
	}
}
