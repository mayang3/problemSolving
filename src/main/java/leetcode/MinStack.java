package leetcode;

import java.util.*;

public class MinStack {

	static class Pair {
		int val;
		int min;

		public Pair(int val, int min) {
			this.val = val;
			this.min = min;
		}
	}
	LinkedList<Pair> stack;

	public MinStack() {
		this.stack = new LinkedList<>();
	}

	public void push(int x) {
		int min = x;

		if (stack.isEmpty() == false) {
			min = Math.min(min, stack.get(stack.size() - 1).min);
		}

		stack.addLast(new Pair(x, min));
	}

	public void pop() {
		if (this.stack == null || this.stack.isEmpty()) {
			return;
		}

		stack.removeLast();
	}

	public int top() {
		if (stack == null || stack.isEmpty()) {
			return 0;
		}

		return stack.getLast().val;
	}

	public int getMin() {
		if (this.stack == null || this.stack.isEmpty()) {
			return 0;
		}

		return stack.getLast().min;
	}
}
