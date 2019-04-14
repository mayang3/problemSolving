package leetcode;

/*
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

	/**
	 * brute force
	 */
//	public int lengthOfLongestSubstring(String s) {
//		int n = s.length();
//		int ans = 0;
//		// O(n3)
//		for (int i = 0; i < n; i++) {
//			for (int j = i + 1; j <= n; j++) {
//
//				if (allUnique(s, i, j)) {
//					// 유니크한 녀석이라면.. 현재 길이를 반환한다.
//					ans = Math.max(ans, j - i);
//				}
//			}
//		}
//
//		return ans;
//	}
//
//	boolean allUnique(String s, int start, int end) {
//		Set<Character> set = new HashSet<>();
//		for (int i = start; i < end; i++) {
//			Character ch = s.charAt(i);
//
//			if (set.contains(ch)) {
//				return false;
//			}
//
//			set.add(ch);
//		}
//		return true;
//	}

	/**
	 * sliding window
	 *
	 * Time Complexity : In the worst case each character will be visited twice by and.
	 * Space complexity : Same as the previous approach. We need space for the sliding window, where is the size of the Set. The size of the Set is upper bounded by the size of the string and the size of the charset/alphabet.
	 *
	 */
	public int lengthOfLongestSubstring(String s) {
		int n = s.length(), ans = 0;
		Map<Character, Integer> map = new HashMap<>(); // current index of character
		// try to extend the range [i, j]
		for (int j = 0, i = 0; j < n; j++) {

			if (map.containsKey(s.charAt(j))) {
				i = Math.max(map.get(s.charAt(j)), i);
			}

			ans = Math.max(ans, j - i + 1);
			map.put(s.charAt(j), j + 1);
		}
		return ans;
	}

	/**
	 * sliding window optimized
	 *
	 * Time complexity : . Index will iterate times.
	 * Space complexity (HashMap) : . Same as the previous approach.
	 * Space complexity (Table): . is the size of the charset.
	 *
	 */
//	public int lengthOfLongestSubstring(String s) {
//		int n = s.length(), ans = 0;
//		Map<Character, Integer> map = new HashMap<>(); // current index of character
//
//		// try to extend the range [i, j]
//		for (int j = 0, i = 0; j < n; j++) {
//			if (map.containsKey(s.charAt(j))) {
//				i = Math.max(map.get(s.charAt(j)), i);
//			}
//			ans = Math.max(ans, j - i + 1);
//			map.put(s.charAt(j), j + 1);
//		}
//
//		return ans;
//	}

	public static void main(String[] args) {
		LongestSubstringWithoutRepeatingCharacters characters = new LongestSubstringWithoutRepeatingCharacters();
		int i = characters.lengthOfLongestSubstring("abcabcbb");

		System.out.println(i);
	}

}
