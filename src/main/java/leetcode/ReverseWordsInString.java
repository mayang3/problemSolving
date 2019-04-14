package leetcode;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author baejunbeom
 */
public class ReverseWordsInString {

	public String reverseWords(String s) {
		StringTokenizer stringTokenizer = new StringTokenizer(s);

		Stack<String> stack = new Stack<>();

		while(stringTokenizer.hasMoreTokens()) {
			stack.push(stringTokenizer.nextToken());
		}

		StringBuilder builder = new StringBuilder();

		while(!stack.isEmpty()) {
			builder.append(stack.pop());

			if (!stack.isEmpty()) {
				builder.append(" ");
			}
		}

		return builder.toString();
	}

	public static void main(String[] args) {
		ReverseWordsInString reverseWordsInString = new ReverseWordsInString();
		String s = reverseWordsInString.reverseWords(" 1");

		System.out.println(s);
	}

}
