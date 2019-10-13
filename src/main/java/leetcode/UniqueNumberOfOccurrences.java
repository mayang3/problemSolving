package leetcode;

import java.util.HashMap;
import java.util.Map;

public class UniqueNumberOfOccurrences {

	public boolean uniqueOccurrences(int[] arr) {
		Map<Integer, Integer> countMap = new HashMap<>();
		Map<Integer, Integer> reverseMap = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {
			countMap.merge(arr[i], 1, Integer::sum);

			int num = countMap.get(arr[i]);

			if (reverseMap.containsKey(num-1)) {
				reverseMap.merge(num-1, -1, Integer::sum);

				if (reverseMap.get(num-1) <= 0) {
					reverseMap.remove(num-1);
				}
			}

			reverseMap.merge(num, 1, Integer::sum);
		}

		return reverseMap.size() == countMap.size();
	}

	public static void main(String[] args) {
		int [] arr = {-3,0,1,-3,1,1,1,-3,10,0};

		UniqueNumberOfOccurrences uniqueNumberOfOccurrences = new UniqueNumberOfOccurrences();

		boolean ret = uniqueNumberOfOccurrences.uniqueOccurrences(arr);

		System.out.println(ret);
	}
}
