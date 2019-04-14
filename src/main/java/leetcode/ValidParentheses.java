package leetcode;

import java.util.Stack;

/**
 * @author baejunbeom
 */
public class ValidParentheses {

	public static void main(String[] args) {
		ValidParentheses parentheses = new ValidParentheses();
		boolean valid = parentheses.isValid("(){[]}");

		System.out.println(valid);
	}

	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();

		char[] chars = s.toCharArray();
		stack.push(chars[0]);

		for (int i = 1 ; i<chars.length ; i++) {
			char ch = chars[i];
			Character peek = null;

			if (stack.isEmpty() == false) {
				peek = stack.peek();

			}
			
			if (isValidBracket(ch, peek)) {
				stack.pop();
				continue;
			}

			stack.push(ch);
		}

		return stack.isEmpty();
	}

	private boolean isValidBracket(char ch, Character peek) {
		if (peek == null) {
			return false;
		}

		if ('(' == peek && ')' == ch) {
			return true;
		}
		
		if ('{' == peek && '}' == ch) {
			return true;
		}

		if ('[' == peek && ']' == ch) {
			return true;
		}
		
		return false;
	}
}
