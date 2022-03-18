package leetcode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
	public List<String> generateParenthesis(int n) {
		List<String> ret = new ArrayList<>();
		solve(ret, "", n, n);
		return ret;
	}

	private void solve(List<String> ret, String s, int left, int right) {
		if (left == 0 && right == 0) {
			ret.add(s);
			return;
		}

		if (left > 0) {
			solve(ret, s + "(", left - 1, right);
		}

		if (left < right && right > 0) {
			solve(ret, s + ")", left, right - 1);
		}
	}

	public static void main(String[] args) {
		GenerateParentheses generateParentheses = new GenerateParentheses();

		System.out.println(generateParentheses.generateParenthesis(1));
	}
}
