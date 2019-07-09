package leetcode;

import java.util.*;

/**
 * @author neo82
 */
public class TopKFrequentElements {
	public static void main(String[] args) {
		int [] nums = {1};
		int k = 1;

		TopKFrequentElements topKFrequentElements = new TopKFrequentElements();
		List<Integer> integers = topKFrequentElements.topKFrequent(nums, k);

		System.out.println(integers);
	}

	public List<Integer> topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			map.merge(nums[i], 1, Integer::sum);
		}

		List<Node> nodeList = new ArrayList<>();

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			nodeList.add(new Node(entry.getKey(), entry.getValue()));
		}

		Collections.sort(nodeList, (o1, o2) -> o2.v - o1.v);

		List<Integer> ret = new ArrayList<>();

		for (int i = 0; i < k; i++) {
			ret.add(nodeList.get(i).k);
		}

		return ret;
	}

	static class Node {
		int k;
		int v;

		Node(int k, int v) {
			this.k = k;
			this.v = v;
		}
	}
}
