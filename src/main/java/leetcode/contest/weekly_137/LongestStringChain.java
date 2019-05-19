package leetcode.contest.weekly_137;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author neo82
 */
public class LongestStringChain {
	public static void main(String[] args) {
		String [] words = {"a","b","ba","bca","bda","bdca"};
		System.out.println(longestStrChain(words));
	}

	public static int longestStrChain(String[] words) {
		Arrays.sort(words, Comparator.comparingInt(String::length));

		Map<String, Integer> dp = new HashMap();
		for (String word : words) {
			dp.put(word, 1);
		}

		int max = 0;
		for (String word : words) {
			for (int i = 0; i < word.length(); ++i) {   // delete one char from current word to find the previous string
				String prev = word.substring(0, i) + word.substring(i+1);
				if (dp.containsKey(prev)) {
					// 현재 word 에 도달하는 방법은 여러가지가 있다.
					// 예를 들어, {a, b, ac, bc, bcd, acbd} 가 있다고 하면,
					// a -> ac -> acbd 로 도달할 수도 있고,
					// b -> bc -> bcd -> acbd 로 도달할 수도 있다.
					// 이럴 경우 최대값은 b -> bc -> bcd -> acbd 가 반영되어야 하므로, dp.get(word) 와 dp.get(prev)+1 이 항상 같이 비교되어야 한다.
					dp.put(word, Math.max(dp.get(word), dp.get(prev) + 1));
				}
			}

			// 현재 단어보다 이전까지 도달한 단어들의 가짓수와 현재 단어에 도달한 가짓수를 비교한다.
			// 현재 단어보다 이전까지 도달한 단어들의 가짓수가 더 많을 수도 있다.
			max = Math.max(max, dp.get(word));
		}

		return max;
	}

}
