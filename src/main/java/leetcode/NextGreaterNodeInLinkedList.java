package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NextGreaterNodeInLinkedList {

	public class ListNode {
		int val;
		ListNode next;

		ListNode() {}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public int[] nextLargerNodes(ListNode head) {
		int n = 0;

		List<Integer> list = new ArrayList<>();

		while (head != null) {
			n++;
			list.add(head.val);
			head = head.next;
		}

		int [] res = new int[n];

		Stack<Pair> stack = new Stack<>();

		for (int i = 0; i < list.size(); i++) {
			int here = list.get(i);

			while (!stack.isEmpty() && stack.peek().val < here) {
				Pair pair = stack.pop();
				res[pair.index] = here;
			}

			stack.add(new Pair(here, i));
		}

		while (!stack.isEmpty()) {
			Pair pair = stack.pop();
			res[pair.index] = 0;
		}

		return res;
	}

	static class Pair {
		int val;
		int index;

		public Pair(int val, int index) {
			this.val = val;
			this.index = index;
		}
	}

	public static void main(String[] args) {

	}
}
