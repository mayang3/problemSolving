package leetcode.contest.weekly_280;

import java.util.*;

public class MaximumANDSumOfArray {
	public int maximumANDSum(int[] nums, int numSlots) {
		int n = nums.length;

		Pair [] pairs = new Pair[numSlots+1];

		for (int i = 0; i < n; i++) {
			for (int slot = 1; slot <= numSlots; slot++) {
				if (pairs[slot] == null) {
					pairs[slot] = new Pair();
				}

				int AND = nums[i] & slot;

				pairs[slot].indexAndMap.put(i, AND);
				pairs[slot].andIndexMap.computeIfAbsent(AND, t -> new TreeSet<>((o1, o2) -> o2 - o1)).add(i);
			}
		}

		int res = 0;
		int [] checker = new int[numSlots+1];

		for (int i = 0; i < n; i++) {
			int maxSlot = -1;
			int maxKey = -1;
			int maxVal = -1;

			// 1. find target
			for (int slot = 1; slot <= numSlots ; slot++) {
				if (pairs[slot].andIndexMap.size() == 0 || checker[slot] >= 2) {
					continue;
				}

				if (pairs[slot].andIndexMap.firstKey() > maxVal) {
					maxSlot = slot;
					maxVal = pairs[slot].andIndexMap.firstKey();
					maxKey = pairs[slot].andIndexMap.firstEntry().getValue().first();
				}
			}

			// 2. select Slot
			checker[maxSlot]++;
			res += maxVal;

			// 3. 해당 키를 모든 slot 에서 제거한다.
			for (int slot = 1; slot <= numSlots ; slot++) {
				if (pairs[slot].andIndexMap.size() == 0) {
					continue;
				}

				int delVal = pairs[slot].indexAndMap.get(maxKey);
				pairs[slot].andIndexMap.get(delVal).remove(maxKey);

				if (pairs[slot].andIndexMap.get(delVal).size() == 0) {
					pairs[slot].andIndexMap.remove(delVal);
				}

				pairs[slot].indexAndMap.remove(maxKey);
			}


		}

		return res;
	}

	static class Pair {
		TreeMap<Integer, TreeSet<Integer>> andIndexMap = new TreeMap<>((o1, o2) -> o2 - o1);
		Map<Integer, Integer> indexAndMap = new HashMap<>();
	}

	public static void main(String[] args) {
		// [14,7,9,8,2,4,11,1,9]
		// 8
		int[] nums = {14,7,9,8,2,4,11,1,9};
		int numSlots = 8;

		MaximumANDSumOfArray maximumANDSumOfArray = new MaximumANDSumOfArray();
		System.out.println(maximumANDSumOfArray.maximumANDSum(nums, numSlots));
	}
}
