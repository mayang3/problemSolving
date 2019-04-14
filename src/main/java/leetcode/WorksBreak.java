package leetcode;

import java.util.*;

/*

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".

 */


/**
 * @author baejunbeom
 */
public class WorksBreak {

	/**
	 * bruteforce -> Time Limit Exceeded
	 *
	 * Time Complexity O(N의 n승)
	 * Space Complexity O(n)
	 *
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public boolean bruteForce(String s, List<String> wordDict) {
		return word_Break(s, new HashSet(wordDict), 0);
	}

	private boolean word_Break(String s, Set<String> wordDict, int start) {
		if (start == s.length()) {
			return true;
		}
		for (int end = start + 1; end <= s.length(); end++) {
			if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * DP solution
	 *
	 * Time Complexity O(n2)
	 * Space Complexity O(n)
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public boolean wordBreak(String s, List<String> wordDict) {
		Set<String> wordDictSet=new HashSet(wordDict);
		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[s.length()];
	}

	public static void main(String[] args) {
		List<String> wordDict = new ArrayList<>();
//		wordDict.add("leet");
//		wordDict.add("code");
		wordDict.add("car");
		wordDict.add("ca");
		wordDict.add("rs");

		WorksBreak worksBreak = new WorksBreak();
		boolean leetcode = worksBreak.wordBreak("cars", wordDict);

		System.out.println(leetcode);
	}
}
