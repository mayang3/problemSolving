package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class LongestValidParenthesis {
	public int longestValidParentheses(String s) {
		Deque<Pair> deque = new LinkedList<>();

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);

			if (ch == ')' && !deque.isEmpty() && deque.peekLast().ch == '(') {
				deque.pollLast();
			} else {
				deque.add(new Pair(i, ch));
			}
		}

		if (deque.isEmpty() || deque.peekFirst().i > 0) {
			deque.addFirst(new Pair(-1, ' '));
		}

		if (deque.isEmpty() || deque.peekLast().i < s.length()-1) {
			deque.addLast(new Pair(s.length(), ' '));
		}

		int len = 0;

		for (int i = 1; i < deque.size(); i++) {
			int left = ((LinkedList<Pair>)deque).get(i-1).i;
			int right = ((LinkedList<Pair>)deque).get(i).i;

			len = Math.max(len, right - left - 1);
		}

		return len;
	}

	static class Pair {
		int i;
		char ch;

		public Pair(int i, char ch) {
			this.i = i;
			this.ch = ch;
		}
	}

	public static void main(String[] args) {
		LongestValidParenthesis longestValidParenthesis = new LongestValidParenthesis();
		System.out.println(longestValidParenthesis.longestValidParentheses("())"));
	}
}
