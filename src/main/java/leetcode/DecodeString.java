package leetcode;

import java.util.Stack;

/**
 * @author neo82
 */
public class DecodeString {

	/**
	 * s = "3[a]2[bc]", return "aaabcbc".
	 * s = "3[a2[c]]", return "accaccacc".
	 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
	 *
	 * @param s
	 * @return
	 */
	public String decodeString(String s) {
		if (s == null || s.isEmpty()) {
			return "";
		}

		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);

			if (ch == ']') {
				StringBuilder sb = new StringBuilder();
				StringBuilder num = new StringBuilder();

				while (stack.isEmpty() == false) {
					char pop = stack.pop();

					if (Character.isDigit(pop)) {
						num.insert(0, pop);
					} else if (num.length() > 0) {
						stack.add(pop);
						break;
					} else if (Character.isAlphabetic(pop)) {
						sb.insert(0, pop);
					}
				}

				int end = new Integer(sb.length());

				for (int j = 0; j < Integer.parseInt(num.toString())-1; j++) {
					sb.append(sb.substring(0, end));
				}

				for (int j = 0; j < sb.length(); j++) {
					stack.add(sb.charAt(j));
				}

			} else {
				stack.add(ch);
			}
		}

		StringBuilder sb = new StringBuilder();

		while (stack.isEmpty() == false) {
			sb.insert(0, stack.pop());
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		DecodeString ds = new DecodeString();

		System.out.println(ds.decodeString("3[a]2[bc]"));
	}
}
