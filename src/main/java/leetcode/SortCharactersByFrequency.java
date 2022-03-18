package leetcode;

import java.util.*;

public class SortCharactersByFrequency {
	public String frequencySort(String s) {
		Map<Character, Integer> countMap = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {
			countMap.merge(s.charAt(i), 1, Integer::sum);
		}

		List<Pair> pairList = new ArrayList<>();

		for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
			pairList.add(new Pair(entry.getKey(), entry.getValue()));
		}

		Collections.sort(pairList, (o1, o2) -> o2.count - o1.count);

		StringBuilder sb = new StringBuilder();

		for (Pair pair : pairList) {
			for (int i = 0; i < pair.count; i++) {
				sb.append(pair.c);
			}
		}

		return sb.toString();
	}

	static class Pair {
		char c;
		int count;

		public Pair(char c, int count) {
			this.c = c;
			this.count = count;
		}
	}

	public static void main(String[] args) {
		SortCharactersByFrequency sortCharactersByFrequency = new SortCharactersByFrequency();
		System.out.println(sortCharactersByFrequency.frequencySort("Aabb"));
	}
}
