package baekjoon.stack;

import java.util.Scanner;
import java.util.Stack;

public class 괄호 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		while (N-- > 0) {
			String input = scanner.next();

			System.out.println(solve(input));
		}
	}


	static String solve(String input) {
		Stack<Character> stack = new Stack<>();
		stack.push(input.charAt(0));

		for (int i=1 ; i<input.length() ; i++) {
			char cur = input.charAt(i);

			if (cur == '(') {
				stack.push(cur);
			} else {
				// cur == ')'
				if (stack.isEmpty() || stack.pop() != '(') {
					return "NO";
				}
			}
		}

		if (!stack.isEmpty()) {
			return "NO";
		}

		return "YES";
	}
}
