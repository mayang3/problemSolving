package leetcode;

import java.util.Stack;

public class DecodeString2 {
	public String decodeString(String s) {
		int n = s.length();

		Stack<String> stack = new Stack<>();

		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);

			if (c == ']') {
				StringBuilder sb = new StringBuilder();

				while (!stack.peek().equals("[")) {
					sb.insert(0, stack.pop());
				}

				// discard left bracket
				stack.pop();

				StringBuilder numStr = new StringBuilder();

				while (!stack.isEmpty() && isDigit(stack.peek())) {
					numStr.insert(0, stack.pop());
				}

				int num = Integer.valueOf(numStr.toString());

				String original = sb.toString();
				String newString = new String();

				for (int j = 0; j < num; j++) {
					newString += original;
				}

				stack.push(newString);

			} else {
				stack.push(String.valueOf(c));
			}
		}

		StringBuilder res = new StringBuilder();

		while (!stack.isEmpty()) {
			res.insert(0, stack.pop());
		}

		return res.toString();
	}

	boolean isDigit(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
		}

		return false;
	}

	public static void main(String[] args) {
		DecodeString2 decodeString2 = new DecodeString2();
		System.out.println(decodeString2.decodeString("100[leetcode]"));
	}
}
