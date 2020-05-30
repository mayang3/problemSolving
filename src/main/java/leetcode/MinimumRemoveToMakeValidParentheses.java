package leetcode;

import java.util.Stack;

public class MinimumRemoveToMakeValidParentheses {
	public String minRemoveToMakeValid(String s) {

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == '(') {
				stack.add(i+1);
			} else if (c == ')') {
				if (!stack.isEmpty() && stack.peek() >= 0) {
					stack.pop();
				} else {
					stack.add(-1 * (i+1));
				}
			}
		}

		StringBuilder sb = new StringBuilder(s);

		// 항상 뒤에서부터 삭제해야만 인덱스가 틀어지지 않는다.
		// stack 의 특성상 맨 위의 값은 가장 맨 뒤의 인덱스가 있기 때문에.. 값이 틀어지지 않는다.
		while (!stack.isEmpty()) {
			sb.deleteCharAt(Math.abs(stack.pop()) - 1);
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		MinimumRemoveToMakeValidParentheses mrtmvp = new MinimumRemoveToMakeValidParentheses();
		String s = mrtmvp.minRemoveToMakeValid("))((");

		System.out.println(s);
	}
}
