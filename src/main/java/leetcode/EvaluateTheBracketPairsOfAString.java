package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluateTheBracketPairsOfAString {
	public String evaluate(String s, List<List<String>> knowledge) {
		Map<String, String> replaceMap = new HashMap<>();

		for (List<String> list : knowledge) {
			replaceMap.put(list.get(0), list.get(1));
		}

		int i = 0;

		StringBuilder sb = new StringBuilder();

		while (i < s.length()) {
			char ch = s.charAt(i);

			if (ch == '(') {
				StringBuilder key = new StringBuilder();
				i++;
				while (s.charAt(i) != ')') {
					key.append(s.charAt(i++));
				}

				String value = replaceMap.get(key.toString());

				if (value == null) {
					sb.append("?");
				} else {
					sb.append(value);
				}

			} else {
				sb.append(ch);
			}

			i++;
		}

		return sb.toString();
	}
}
