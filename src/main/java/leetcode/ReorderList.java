package leetcode;

public class ReorderList {

	public static class ListNode {
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

	public void reorderList(ListNode head) {
		while (head != null && head.next != null) {
			ListNode last = getLast(head);
			last.next = head.next;
			head.next = last;
			head = last.next;
		}
	}

	private ListNode getLast(ListNode head) {
		ListNode cur = head;

		while (cur != null) {
			if (cur.next.next == null) {
				ListNode last = cur.next;
				cur.next = null;
				return last;
			}

			cur = cur.next;
		}

		return null;
	}

	public static void main(String[] args) {
		ListNode node4 = new ListNode(5);
		ListNode node3 = new ListNode(4);
		ListNode node2 = new ListNode(3);
		ListNode node1 = new ListNode(2);
		ListNode root = new ListNode(1);

		root.next = node1;
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;

		ReorderList reorderList = new ReorderList();
		reorderList.reorderList(root);

		print(root);
	}

	private static void print(ListNode root) {
		while (root != null) {
			System.out.println(root.val);
			root = root.next;
		}
	}
}
