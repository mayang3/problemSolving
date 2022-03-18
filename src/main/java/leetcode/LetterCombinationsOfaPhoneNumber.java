package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfaPhoneNumber {
	public List<String> letterCombinations(String digits) {
		List<String> dict = new ArrayList<>();

		dict.add(""); // 0
		dict.add(""); // 1
		dict.add("abc"); // 2
		dict.add("def");
		dict.add("ghi");
		dict.add("jkl");
		dict.add("mno");
		dict.add("pqrs");
		dict.add("tuv");
		dict.add("wxyz");

		Map<Integer, List<String>> dp = new HashMap<>();

		return solve(dp, dict, digits, 0);
	}

	private List<String> solve(Map<Integer, List<String>> dp, List<String> dict, String digits, int i) {
		if (i >= digits.length()) {
			return new ArrayList<>();
		}

		if (dp.containsKey(i) == false) {

			int num = Character.getNumericValue(digits.charAt(i));

			List<String> res = new ArrayList<>();

			for (char ch : dict.get(num).toCharArray()) {
				List<String> subList = solve(dp, dict, digits, i + 1);

				if (subList == null || subList.isEmpty()) {
					res.add(ch + "");
				} else {
					for (String subStr : subList) {
						res.add(ch + subStr);
					}
				}
			}

			dp.put(i, res);
		}

		return dp.get(i);
	}

	public static void main(String[] args) {
		LetterCombinationsOfaPhoneNumber letterCombinationsOfaPhoneNumber = new LetterCombinationsOfaPhoneNumber();
		System.out.println(letterCombinationsOfaPhoneNumber.letterCombinations("2"));
	}
}
