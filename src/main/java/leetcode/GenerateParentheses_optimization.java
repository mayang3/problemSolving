package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neo82
 */
public class GenerateParentheses_optimization {
	public List<String> generateParenthesis(int n) {
		List<String> ret = new ArrayList<>();

		solve(ret, "", n, n);

		return ret;
	}


	public void solve(List<String> ret, String parenthesis, int l, int r) {
		if (l == 0 && r == 0) {
			ret.add(parenthesis);
		}

		if (l > 0) {
			solve(ret, parenthesis + "(", l - 1, r);
		}

		// 왼쪽 괄호가 열린 상태에서만 닫는 괄호를 넣을 수 있다.
		if (l < r) {
			solve(ret, parenthesis + ")", l, r - 1);
		}
	}

	public static void main(String[] args) {
		GenerateParentheses_optimization go = new GenerateParentheses_optimization();

		List<String> strings = go.generateParenthesis(3);

		for (String s : strings) {
			System.out.println(s);
		}
	}
}
