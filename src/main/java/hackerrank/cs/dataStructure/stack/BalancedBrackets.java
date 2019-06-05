package hackerrank.cs.dataStructure.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author neo82
 */
public class BalancedBrackets {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		for (int i = 0; i < n; i++) {
			System.out.println(isBalanced(scanner.next()));
		}
	}

	static String isBalanced(String s) {

		Stack<Character> stack = new Stack<>();

		for (char ch : s.toCharArray()) {
			if (isCloseBracket(ch) == false) {
				stack.push(ch);
			} else if (isCloseBracket(ch) && stack.isEmpty()) {
				return "NO";
			} else if (ch == ')' && stack.pop() != '('){
				return "NO";
			} else if (ch == '}' && stack.pop() != '{') {
				return "NO";
			} else if (ch == ']' && stack.pop() != '[') {
				return "NO";
			}
		}

		return stack.isEmpty() ? "YES" : "NO";
	}

	static boolean isCloseBracket(char ch) {
		return ch == ')' || ch == '}' || ch == ']';
	}
}
