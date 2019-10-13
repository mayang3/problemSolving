package leetcode;

import java.util.Stack;

public class RemoveAllAdjacentDuplicatesInString2 {
	public String removeDuplicates(String s, int k) {
		Stack<Character> charStack = new Stack<>();
		Stack<Integer> counterStack = new Stack<>();

		for (int i = s.length() - 1; i >= 0 ; i--) {
			char ch = s.charAt(i);

			if (!counterStack.isEmpty() && ch == charStack.peek()) {
				counterStack.push(counterStack.peek() + 1);
			} else {
				counterStack.push(1);
			}

			charStack.push(ch);

			if (counterStack.peek() == k) {
				for (int j = 0; j < k; j++) {
					charStack.pop();
					counterStack.pop();
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		while (charStack.isEmpty() == false) {
			sb.append(charStack.pop());
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		RemoveAllAdjacentDuplicatesInString2 string2 = new RemoveAllAdjacentDuplicatesInString2();

		String ret = string2.removeDuplicates("pbbcggttciiippooaais", 2);

		System.out.println(ret);
	}
}
