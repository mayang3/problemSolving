package leetcode;

/**
 * @author neo82
 */
public class ValidParenthesisString {
	public boolean checkValidString(String s) {
		return checkValidString(s, 0, 0, 0);
	}

	public boolean checkValidString(String s, int i, int open, int close) {
		if (s.length() == i) {
			return open == close;
		}

		if (close > open) {
			return false;
		}

		switch (s.charAt(i)) {
			case '(':
				return checkValidString(s, i+1, open+1, close);
			case ')':
				return checkValidString(s, i+1, open, close+1);
			case '*':
				return checkValidString(s, i+1, open, close) || checkValidString(s, i+1, open+1, close) || checkValidString(s, i+1, open, close+1);
		}

		return false;
	}

	public static void main(String[] args) {
		ValidParenthesisString vps = new ValidParenthesisString();

		System.out.println(vps.checkValidString("(*))"));
	}
}
