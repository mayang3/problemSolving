package leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReduceArraySizeToTheHalf {
	public int minSetSize(int[] arr) {
		int n = arr.length;

		Map<Integer, Integer> countMap = new HashMap<>();

		for (int i = 0; i < n; i++) {
			countMap.merge(arr[i], 1, Integer::sum);
		}

		PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());

		for (Map.Entry<Integer, Integer> e : countMap.entrySet()) {
			pq.add(e);
		}

		int min = 0;
		while (!pq.isEmpty() && n > arr.length / 2) {
			Map.Entry<Integer, Integer> poll = pq.poll();
			n -= poll.getValue();
			min++;
		}

		return min;
	}

	public static void main(String[] args) {
		int [] arr = {1,2,3,3,5,6};

		ReduceArraySizeToTheHalf reduceArraySizeToTheHalf = new ReduceArraySizeToTheHalf();
		System.out.println(reduceArraySizeToTheHalf.minSetSize(arr));
	}
}
