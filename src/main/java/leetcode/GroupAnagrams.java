package leetcode;

import java.util.*;

/**
 * @author baejunbeom
 */
public class GroupAnagrams {

	Map<String, List<String>> map = new HashMap<>();

	public List<List<String>> groupAnagrams(String[] strs) {

		for (String str : strs) {
			char[] value = str.toCharArray();
			char[] keyArr = Arrays.copyOf(value, value.length);
			Arrays.sort(keyArr);
			String key = new String(keyArr);
			String sv = new String(value);

			if (map.containsKey(key)) {
				map.get(key).add(sv);
			} else {
				List<String> list = new ArrayList<>();
				list.add(sv);
				map.put(key, list);
			}
		}

		return new ArrayList<>(map.values());
	}

	public static void main(String[] args) {
		final String [] input = {
			"eat", "tea", "tan", "ate", "nat", "bat"
		};

		GroupAnagrams groupAnagrams = new GroupAnagrams();
		List<List<String>> lists = groupAnagrams.groupAnagrams(input);

		System.out.println(lists);
	}


}
