package leetcode;

import java.util.Stack;

public class AddTwoNumbers3 {
	public static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		Stack<Integer> stack1 = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();

		while (l1 != null) {
			stack1.add(l1.val);
			l1 = l1.next;
		}

		while (l2 != null) {
			stack2.add(l2.val);
			l2 = l2.next;
		}

		ListNode res = null;
		int remain = 0;

		while (stack1.isEmpty() == false || stack2.isEmpty() == false || remain > 0) {
			int val1 = 0;
			int val2 = 0;

			if (stack1.isEmpty() == false) {
				val1 = stack1.pop();
			}

			if (stack2.isEmpty() == false) {
				val2 = stack2.pop();
			}
			
			int sum = val1 + val2 + remain;

			ListNode newNode = new ListNode(sum % 10, res);
			res = newNode;
			remain = sum / 10;
		}
		
		return res;
	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(5);

		ListNode l2 = new ListNode(5);


		AddTwoNumbers3 addTwoNumbers2 = new AddTwoNumbers3();

		ListNode listNode = addTwoNumbers2.addTwoNumbers(l1, l2);

		while (listNode != null) {
			System.out.println(listNode.val);
			listNode = listNode.next;
		}
	}
}
