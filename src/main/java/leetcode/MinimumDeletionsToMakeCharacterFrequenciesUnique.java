package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class MinimumDeletionsToMakeCharacterFrequenciesUnique {
	public int minDeletions(String s) {
		Map<Character, Integer> countMap = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {
			countMap.merge(s.charAt(i), 1, Integer::sum);
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

		for (int val : countMap.values()) {
			pq.add(val);
		}

		int res = 0;

		Set<Integer> countSet = new HashSet<>();

		while (pq.isEmpty() == false) {
			int poll = pq.poll();

			while (poll != 0 && countSet.contains(poll)) {
				poll--;
				res++;
			}

			if (poll != 0) {
				countSet.add(poll);
			}
		}

		return res;
	}


	public static void main(String[] args) {
		String s = "bbcebab";

		MinimumDeletionsToMakeCharacterFrequenciesUnique minimumDeletionsToMakeCharacterFrequenciesUnique = new MinimumDeletionsToMakeCharacterFrequenciesUnique();
		System.out.println(minimumDeletionsToMakeCharacterFrequenciesUnique.minDeletions(s));
	}
}
