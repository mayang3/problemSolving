package leetcode;

public class AddTwoNumbers2 {

	 public static  class ListNode {
	     int val;
	     ListNode next;
	     ListNode() {}
	     ListNode(int val) { this.val = val; }
	     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	 }


	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	 	l1 = reverse(l1);
	 	l2 = reverse(l2);

	 	ListNode head = new ListNode(-1);
	 	ListNode cur = head;

	 	int remain = 0;

	 	while (l1 != null || l2 != null || remain > 0) {
	 		int l1Value = l1 != null ? l1.val : 0;
	 		int l2Value = l2 != null ? l2.val : 0;

	 		int sum = l1Value + l2Value + remain;

			cur.next = new ListNode(sum % 10);
	 		remain = sum / 10;

	 		cur = cur.next;
	 		if (l1 != null) {
	 			l1 = l1.next;
			}

	 		if (l2 != null) {
	 			l2 = l2.next;
			}
		}

	 	head = head.next;

	 	return reverse(head);
	}

	private ListNode reverse(ListNode listNode) {
	 	if (listNode.next == null) {
	 		return listNode;
		}

	 	ListNode dummy = new ListNode(-1, listNode);

		ListNode slow = dummy;
		ListNode fast = dummy.next;

		while (fast != null) {
			ListNode next = fast.next;

			if (slow.val == -1) {
				fast.next = null;
			} else {
				fast.next = slow;
			}

			slow = fast;
			fast = next;
		}

		return slow;

	}

	public static void main(String[] args) {

	 	ListNode l1 = new ListNode(7);
	 	l1.next = new ListNode(2);
	 	l1.next.next = new ListNode(4);
		l1.next.next.next = new ListNode(3);

		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);


	 	AddTwoNumbers2 addTwoNumbers2 = new AddTwoNumbers2();

		ListNode listNode = addTwoNumbers2.addTwoNumbers(l1, l2);

		while (listNode != null) {
			System.out.println(listNode.val);
			listNode = listNode.next;
		}
	}
}
