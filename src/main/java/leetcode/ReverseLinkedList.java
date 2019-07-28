package leetcode;

public class ReverseLinkedList {

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	// iterative
//	public ListNode reverseList(ListNode head) {
//		// newHead 는 이전값
//		ListNode newHead = null;
//
//		while (head != null) {
//			ListNode next = head.next;
//			head.next = newHead;
//			newHead = head;
//			head = next;
//		}
//
//		return newHead;
//	}

	public ListNode reverseList(ListNode head) {
		return solve(head, null);
	}

	public ListNode solve(ListNode head, ListNode newHead) {
		if (head == null) {
			return newHead;
		}

		ListNode next = head.next;
		head.next = newHead;

		return solve(next, head);
	}




	public static void main(String[] args) {
		ListNode listNode5 = new ListNode(5);

		ListNode listNode4 = new ListNode(4);
		listNode4.next = listNode5;

		ListNode listNode3 = new ListNode(3);
		listNode3.next = listNode4;

		ListNode listNode2 = new ListNode(2);
		listNode2.next = listNode3;

		ListNode listNode1 = new ListNode(1);
		listNode1.next = listNode2;

		ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
		ListNode ret = reverseLinkedList.reverseList(listNode1);

		while (ret != null) {
			System.out.print(ret.val + "->");
			ret = ret.next;
		}
	}
}
