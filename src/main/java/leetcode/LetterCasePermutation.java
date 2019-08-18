package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {
	public List<String> letterCasePermutation(String S) {
		List<String> perm = new ArrayList<>();

		solve(perm, S, new StringBuilder(), 0);

		return perm;
	}

	private void solve(List<String> perm, String s, StringBuilder permS, int i) {
		int len = s.length();

		if (i == len) {
			perm.add(permS.toString());
			return;
		}

		char c = s.charAt(i);

		if (Character.isDigit(c) == false) {
			// 1. lowerCase
			solve(perm, s, permS.append(String.valueOf(c).toLowerCase()), i+1);

			permS.deleteCharAt(permS.length()-1);

			// 2. upperCase
			solve(perm, s, permS.append(String.valueOf(c).toUpperCase()), i+1);

			permS.deleteCharAt(permS.length()-1);
		} else {
			// 3. isDigit
			solve(perm, s, permS.append(c), i+1);

			permS.deleteCharAt(permS.length()-1);
		}
	}

	public static void main(String[] args) {
		LetterCasePermutation lcp = new LetterCasePermutation();

		System.out.println(lcp.letterCasePermutation("3z4"));
	}
}
