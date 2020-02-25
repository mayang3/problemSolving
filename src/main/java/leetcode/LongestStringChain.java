package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author neo82
 */
public class LongestStringChain {

	/**
	 *
	 * Sort the strings by their lengths
	 * Start from shorter words (bottom up DP)
	 * During the loop:
	 *
	 * for each word, iterate through its length and each time delete 1 char.
	 *
	 * Update the longest string chain length in the hashmap for the current word.
	 *
	 * @param words
	 * @return
	 */
	public int longestStrChain(String[] words) {
		if (words == null || words.length == 0) return 0;
		int res = 0;

		// character 가 더 많은 단어에서 하나씩 지워서 찾으므로 반드시 정렬되어야 한다.
		Arrays.sort(words, Comparator.comparingInt(String::length));

		// 현재 word 가 가지고 있는 최대 length
		Map<String, Integer> map = new HashMap();

		// O(n * m)
		for (String word : words) {
			if (map.containsKey(word)) {
				continue;
			}

			// default chain count 는 1개
			map.put(word, 1);

			for (int i = 0; i < word.length(); i++) {
				StringBuilder sb = new StringBuilder(word);

				sb.deleteCharAt(i);

				String next = sb.toString();

				if (map.containsKey(next) && map.get(next) + 1 > map.get(word)) {
					map.put(word, map.get(next) + 1);
				}
			}

			// 현재 단어까지의 최대길이가 곧 전체 최대 길이가 될 수 있으므로, 각 단어마다 갱신해준다.
			res = Math.max(res, map.get(word));
		}

		return res;
	}



	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		String [] words = {"ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"};

		LongestStringChain lsc = new LongestStringChain();

		int res = lsc.longestStrChain(words);

		System.out.println(res);
	}
}
