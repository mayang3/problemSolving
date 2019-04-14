package leetcode.contest;

import java.util.Scanner;
import java.util.Stack;

/**
 */
public class ValidParenthesisString {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String s = scanner.next();

		checkValidString(s);
	}

	public static void checkValidString(String s) {
		Stack<Character> stack = new Stack<>();

		Boolean [] dp = new Boolean[s.length()+1];

		System.out.println(solve(dp, s, 0, stack));
	}

	static boolean solve(Boolean [] dp, String s, int i, Stack<Character> stack) {

		if (dp[i] != null) {
			return dp[i];
		}

		for (int j = i; j < s.length(); j++) {
			char c = s.charAt(j);

			if (c == ')') {
				if (stack.isEmpty() || stack.pop() != '(') {
					return dp[i] = false;
				}
			} else if (c == '(') {
				stack.push(c);
			} else if (c == '*') {
				// 1. empty
				Stack<Character> stack2 = new Stack<>();
				stack2.addAll(stack);

				if (solve(dp, s, j + 1, stack2)) {
					return dp[i] = true;
				}

				// 2. (
				Stack<Character> stack1 = new Stack<>();
				stack1.addAll(stack);
				stack1.push('(');
				if (solve(dp, s, j + 1, stack1)) {
					return dp[i] = true;
				}

				// 3. )
				if (stack.isEmpty() || stack.pop() != '(') {
					return dp[i] = false;
				}
			}
		}

		return dp[i] = stack.isEmpty();
	}
}
