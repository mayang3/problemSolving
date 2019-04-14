package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author baejunbeom
 */
public class LetterCombinationOfaPhoneNumber {

	/**
	 * backtracking 문제.. 모든 가능한 조합을 찾는 경우..
	 * @param digits
	 * @return
	 */
	public List<String> letterCombinations(String digits) {
		// 각 단계를 건널때마다 조합의 수는 3의 배수로 늘어난다.
		// 예를 들어, 첫단계에서는 a 의 수는 1개, 번호가 두개라면 a로 시작하는 수는 3개, 번호개 세개라면 a로 시작하는 수는 9개, 번호가 네개라면 a로 시작하는수는 27개

		if (digits == null || digits.length() <= 0) {
			return new LinkedList<>();
		}

		String [] keys = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

		LinkedList<String> ans = new LinkedList<>();

		ans.add("");

		for (int i=0 ; i<digits.length() ; i++) {
			int numericValue = Character.getNumericValue(digits.charAt(i));

			while (ans.peek().length() == i) {
				String remove = ans.remove();

				for (char ch : keys[numericValue].toCharArray()) {
					ans.add(remove + ch);
				}
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		LetterCombinationOfaPhoneNumber number = new LetterCombinationOfaPhoneNumber();
		List<String> strings = number.letterCombinations("2345");

		String temp = "adgj, adgk, adgl, adhj, adhk, adhl, adij, adik, adil, aegj, aegk, aegl, aehj, aehk, aehl, aeij, aeik, aeil, afgj, afgk, afgl, afhj, afhk, afhl, afij, afik, afil";

		System.out.println(temp.split(",").length);
	}
}
