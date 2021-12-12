package leetcode;

import java.util.*;

public class LeastNumberOfUniqueIntegersAfterKRemovals {

	public int findLeastNumOfUniqueInts(int[] arr, int k) {
		Map<Integer, Integer> countMap = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {
			countMap.merge(arr[i], 1, Integer::sum);
		}

		Map.Entry<Integer, Integer>[] pairs = new Map.Entry[countMap.size()];

		int i = 0;

		for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
			pairs[i++] = entry;
		}

		Arrays.sort(pairs, Comparator.comparingInt(Map.Entry::getValue));

		int n = pairs.length;

		for (i = 0; i < pairs.length; i++) {
			Map.Entry<Integer, Integer> pair = pairs[i];

			int val = pair.getValue();

			// 1. k < val : 현재 element 포함, 남은 element 갯수가 정답이다.
			// 2. k == val : 현재 element 제외, 남은 element 갯수가 정답이다.
			// 3. k > val : k 에서 val 만큼을 제외한 후 다음 step 을 진행한다.
			if (k > val) {
				k -= val;
			} else if (k == val) {
				return n - i - 1;
			} else {
				return n - i;
			}
		}

		return -1;
	}


	public static void main(String[] args) {
		int [] arr = {4,3,1,1,3,3,2};
		int k = 3;

		LeastNumberOfUniqueIntegersAfterKRemovals leastNumberOfUniqueIntegersAfterKRemovals = new LeastNumberOfUniqueIntegersAfterKRemovals();
		System.out.println(leastNumberOfUniqueIntegersAfterKRemovals.findLeastNumOfUniqueInts(arr, k));
	}
}
