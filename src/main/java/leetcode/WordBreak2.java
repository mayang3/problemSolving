package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author neo82
 */
public class WordBreak2 {
	public List<String> wordBreak(String s, List<String> wordDict) {
		return solve(s, wordDict, new HashMap<>());
	}

	private List<String> solve(String s, List<String> wordDict, Map<String, List<String>> map) {
		if (map.containsKey(s)) {
			return map.get(s);
		}

		List<String> returnList = new ArrayList<>();

		for (String w : wordDict) {
			if (s.startsWith(w)) {
				String next = s.substring(w.length());
				if (next.length() == 0) {
					returnList.add(w);
				} else {
					for (String sub : solve(s.substring(w.length()), wordDict, map)) {
						returnList.add(w + " " + sub);
					}
				}
			}
		}

		map.put(s, returnList);

		return returnList;
	}

	public static void main(String[] args) {
		WordBreak2 wb2 = new WordBreak2();

		String s = "catsanddog";
		List<String> wb = new ArrayList<String>();

		// ["cat", "cats", "and", "sand", "dog"]
		wb.add("cat");
		wb.add("cats");
		wb.add("and");
		wb.add("sand");
		wb.add("dog");

		List<String> strings = wb2.wordBreak(s, wb);

		System.out.println(strings);
	}
}
