package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring(String s) {
		int max = 0;

		int i = 0;
		int j = 0;

		Set<Character> cache = new HashSet<>();

		while (j < s.length()) {
			char ch = s.charAt(j);
			// 중복이 없는 경우
			if (cache.contains(ch) == false) {
				cache.add(ch);
				max = Math.max(max, j - i + 1);
				j++;
			} else {
				// 중복된 경우
				cache.remove(s.charAt(i));
				i++;
			}
		}

		return max;
	}

	public static void main(String[] args) {
		LongestSubstringWithoutRepeatingCharacters l = new LongestSubstringWithoutRepeatingCharacters();

		System.out.println(l.lengthOfLongestSubstring(" "));
	}
}
