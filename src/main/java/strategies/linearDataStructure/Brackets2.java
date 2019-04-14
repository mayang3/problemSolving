package strategies.linearDataStructure;

import java.util.Stack;

/**
 * @author baejunbeom
 */
public class Brackets2 {

	static boolean yesOrNo(String input) {
		char[] chars = input.toCharArray();

		Stack<Character> stack = new Stack<>();

		stack.push(chars[0]);

		for (int i=1 ; i<chars.length ; i++) {

			if (chars[i] == ')' || chars[i] == '}' || chars[i] == ']') {
				Character pop = stack.pop();

				if (chars[i] == ')' && pop == '(') {
					continue;
				} else if (chars[i] == '}' && pop == '{') {
					continue;
				} else if (chars[i] == ']' && pop == '[') {
					continue;
				}

				stack.push(pop);
			}

			stack.push(chars[i]);
		}

		return stack.empty();
	}

	public static void main(String[] args) {
		boolean b = yesOrNo("({}[(){}])");

		System.out.println(b);
	}
}
