package leetcode;

import java.util.*;

/**
 * @author neo82
 */
public class TopKFrequentElements {
	public static void main(String[] args) {
		int [] nums = {1,1,1,2,2,3};
		int k = 2;

		TopKFrequentElements topKFrequentElements = new TopKFrequentElements();
		System.out.println(Arrays.toString(topKFrequentElements.topKFrequent(nums, k)));
	}

	public int [] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			map.merge(nums[i], 1, Integer::sum);
		}

		List<Integer> [] bucket = new List[nums.length+1];

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int freq = entry.getValue();

			if (bucket[freq] == null) {
				bucket[freq] = new ArrayList<>();
			}

			bucket[freq].add(entry.getKey());
		}

		int i = 0;
		int [] res = new int[k];

		for (int j = bucket.length-1; j >=0; j--) {
			if (bucket[j] != null) {
				for (int key : bucket[j]) {
					res[i++] = key;

					if (i == k) {
						return res;
					}
				}
			}
		}

		return res;
	}
}
