package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author baejunbeom
 */
public class TwoSum {

	/**
	 * brute force..
	 *
	 * Time Complexity O(n2)
	 * Space Complexity O(1)
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] nums, int target) {

		for (int i=0 ; i<nums.length-1 ; i++) {
			for (int j=i+1 ; j<nums.length ; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[] {i, j};
				}
			}
		}

		return null;
	}

	/**
	 * Time Complexity O(n)
	 * Space Complexity O(n) - HashTable 공간만큼 사용하므로..
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSumHashTable(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i=0 ; i<nums.length ; i++) {
			int complement = target - nums[i];

			if (map.containsKey(complement)) {
				return new int[] {map.get(complement), i};
			} else {
				map.put(nums[i], i);
			}
		}

		return null;
	}

	public static void main(String[] args) {
		TwoSum twoSum = new TwoSum();
//		int[] ints = twoSum.twoSum(new int[] {2, 7, 11, 15}, 9);
		int[] ints = twoSum.twoSumHashTable(new int[] {3,2,4}, 6);

		System.out.println(Arrays.toString(ints));

	}
}
