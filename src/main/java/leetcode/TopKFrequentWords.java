package leetcode;

import java.util.*;

public class TopKFrequentWords {
	public List<String> topKFrequent(String[] words, int k) {
		Map<String, Integer> countMap = new HashMap<>(words.length);

		for (String word : words) {
			countMap.merge(word, 1, Integer::sum);
		}

		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.getValue() != o2.getValue()) {
				return o1.getValue() - o2.getValue();
			}

			return o2.getKey().compareTo(o1.getKey());
		});

		for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
			pq.add(entry);

			if (pq.size() > k) {
				pq.poll();
			}
		}

		LinkedList<String> retList = new LinkedList<>();

		while (pq.isEmpty() == false) {
			retList.add(0, pq.poll().getKey());
		}

		return retList;
	}

	public static void main(String[] args) {
		String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
		int k = 4;

		TopKFrequentWords topKFrequentWords = new TopKFrequentWords();
		List<String> ret = topKFrequentWords.topKFrequent(words, k);

		System.out.println(ret);
	}
}
