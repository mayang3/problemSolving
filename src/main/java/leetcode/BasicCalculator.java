package leetcode;

import java.util.Stack;

public class BasicCalculator {
	static int LEFT_PARENTHESIS = Integer.MAX_VALUE;
	static int MINUS = Integer.MAX_VALUE - 1;

	public int calculate(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		s = s.replaceAll(" ", "");

		int n = s.length();
		int i = 0;

		Stack<Integer> stack = new Stack<>();

		while (i < n) {
			char ch = s.charAt(i);

			if (ch == '+') {
				i++;
				continue;
			}

			if (Character.isDigit(ch)) {
				StringBuilder num = new StringBuilder();
				num.append(ch);

				while (i < n-1 && Character.isDigit(s.charAt(i+1))) {
					num.append(s.charAt(++i));
				}

				stack.add(Integer.valueOf(num.toString()));
			} else if (ch == '(') {
				stack.add(LEFT_PARENTHESIS);
			} else if (ch == '-') {
				if (s.charAt(i+1) == '(') {
					stack.add(MINUS);
				} else {
					StringBuilder num = new StringBuilder(ch);
					num.append(ch);

					while (i < n-1 && Character.isDigit(s.charAt(i+1))) {
						num.append(s.charAt(++i));
					}
					// next value is numeric
					stack.add((Integer.valueOf(num.toString())));
				}
			} else if (ch == ')') {
				int sum = 0;

				while (stack.peek() != Integer.MAX_VALUE) {
					sum += stack.pop();
				}

				stack.pop();

				if (!stack.isEmpty() && stack.peek() == MINUS) {
					sum = -sum;
					stack.pop();
				}

				stack.add(sum);
			}
			i++;
		}

		int sum = 0;

		while (stack.isEmpty() == false) {
			sum += stack.pop();
		}

		return sum;
	}

	public static void main(String[] args) {
		BasicCalculator basicCalculator = new BasicCalculator();
		System.out.println(basicCalculator.calculate("(1+(4+5+2)-3)+(6+8)"));

		//		System.out.println((int)('('));
	}
}
