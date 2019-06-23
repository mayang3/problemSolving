package leetcode;

import java.util.*;

/**
 * @author neo82
 */
public class GenerateParentheses_bruteForce {

	public List<String> generateParenthesis(int n) {
		Set<String> ret = new HashSet<>();
		List<String> parenthesis = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			parenthesis.add("(");
			parenthesis.add(")");
		}

		boolean [] visited = new boolean[n * 2];

		solve(ret, new StringBuilder(), parenthesis, visited);

		return new ArrayList<>(ret);
	}

	public void solve(Set<String> ret, StringBuilder sb, List<String> data, boolean [] visited) {
		if (sb.toString().length() == data.size()) {
			if (isValid(sb.toString()) && !ret.contains(sb.toString())) {
				ret.add(sb.toString());
			}

			return;
		}

		for (int i = 0; i < data.size(); i++) {
			if (visited[i]) {
				continue;
			}

			visited[i] = true;
			sb.append(data.get(i));

			solve(ret,sb, data, visited);

			visited[i] = false;
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	private boolean isValid(String parenthesis) {
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < parenthesis.length(); i++) {
			char ch = parenthesis.charAt(i);

			if (ch == '(') {
				stack.push(ch);
			} else {
				if (stack.isEmpty()) {
					return false;
				}

				if (stack.pop() != '(') {
					return false;
				}
			}
		}

		return stack.isEmpty();
	}

	public static void main(String[] args) {
		GenerateParentheses_bruteForce parentheses = new GenerateParentheses_bruteForce();
		List<String> parenthesis = parentheses.generateParenthesis(5);

		System.out.println(parenthesis);
	}
}
