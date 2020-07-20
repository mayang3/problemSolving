package leetcode;

import java.util.Stack;

public class BasicCalculator2 {
	public int calculate(String s) {
		int len;
		if (s == null || (len = s.length()) == 0) {
			return 0;
		}
		Stack<Integer> stack = new Stack<Integer>();
		int num = 0;
		char sign = '+';
		for (int i = 0; i < len; i++) {
			if (Character.isDigit(s.charAt(i))) {
				num = num * 10 + s.charAt(i) - '0';
			}
			if ((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i)) || i == len - 1) {
				if (sign == '-') {
					stack.push(-num);
				}
				if (sign == '+') {
					stack.push(num);
				}
				if (sign == '*') {
					stack.push(stack.pop() * num);
				}
				if (sign == '/') {
					stack.push(stack.pop() / num);
				}
				sign = s.charAt(i);
				num = 0;
			}
		}

		int re = 0;
		for (int i : stack) {
			re += i;
		}
		return re;
	}

	public static void main(String[] args) {
		BasicCalculator2 basicCalculator2 = new BasicCalculator2();
		int res = basicCalculator2.calculate("1+1+1");

		System.out.println(res);

		//		System.out.println((int)'+'); // 43
		//		System.out.println((int)'-'); // 45
		//		System.out.println((int)'*'); // 42
		//		System.out.println((int)'/'); // 47
	}
}
