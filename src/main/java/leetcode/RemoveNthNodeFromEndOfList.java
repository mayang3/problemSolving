package leetcode;

/**
 *
 * @author neo82
 */
public class RemoveNthNodeFromEndOfList {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	static class IntegerWrapper {
		int n;

		IntegerWrapper(int n) {
			this.n = n;
		}
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		dummy.next = head;

		solve(dummy, new IntegerWrapper(n));

		return dummy.next;
	}

	private void solve(ListNode head, IntegerWrapper iw) {
		if (head.next == null) {
			iw.n--;
			return;
		}

		solve(head.next, iw);
		iw.n--;

		if (iw.n == -1) {
			head.next = head.next.next;
		}
	}

	public static void main(String[] args) {
		ListNode node5 = new ListNode(5);
		ListNode node4 = new ListNode(4);
		node4.next = node5;

		ListNode node3 = new ListNode(3);
		node3.next = node4;

		ListNode node2 = new ListNode(2);
		node2.next = node3;

		ListNode node1 = new ListNode(1);
		node1.next = node2;


		RemoveNthNodeFromEndOfList removeNthNodeFromEndOfList = new RemoveNthNodeFromEndOfList();

		ListNode listNode = removeNthNodeFromEndOfList.removeNthFromEnd(node1, 2);

		while (listNode != null) {
			System.out.println(listNode.val);
			listNode = listNode.next;
		}

	}
}
