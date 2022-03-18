package leetcode;

import java.util.HashMap;
import java.util.Map;

public class DotProductSparseVector {
	static class SparseVector {
		Map<Integer, Integer> numsMap = new HashMap<>();

		SparseVector(int[] nums) {
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] > 0) {
					numsMap.put(i, nums[i]);
				}
			}
		}

		// Return the dotProduct of two sparse vectors
		public int dotProduct(SparseVector vec) {
			int sum = 0;

			for (Map.Entry<Integer, Integer> entry : this.numsMap.entrySet()) {
				if (vec.numsMap.containsKey(entry.getKey())) {
					sum += (entry.getValue() * vec.numsMap.get(entry.getKey()));
				}
			}

			return sum;
		}
	}

	public static void main(String[] args) {
	}
}
