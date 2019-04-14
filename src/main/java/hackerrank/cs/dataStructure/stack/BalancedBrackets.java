package hackerrank.cs.dataStructure.stack;

import java.util.Scanner;
import java.util.Stack;

public class BalancedBrackets {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		while (n-- > 0) {
			String line = scanner.next();

			solve(line);
		}
	}

	static void solve(String line) {
		Stack<Character> st = new Stack<Character>();

		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);

			if (ch == '{' || ch == '[' || ch == '(') {
				st.push(ch);
			} else if (st.isEmpty()) {
				System.out.println("NO");
				return;
			} else if (isValid(st.pop(), ch) == false) {
				System.out.println("NO");
				return;
			}
		}

		if (st.isEmpty() == false) {
			System.out.println("NO");
			return;
		}

		System.out.println("YES");
	}

	static boolean isValid(char open, char close) {
		if (open != '(' && close == ')') {
			return false;
		} else if (open != '[' && close == ']') {
			return false;
		} else if (open != '{' && close == '}') {
			return false;
		}

		return true;
	}
}
