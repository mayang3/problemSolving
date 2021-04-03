package leetcode;

import java.sql.ClientInfoStatus;

public class MergeKSortedLists {


	 public static class ListNode {
	    int val;
	    ListNode next;
	    ListNode() {}
	    ListNode(int val) { this.val = val; }
	    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public ListNode mergeKLists(ListNode[] lists) {
	 	return solve(lists, 0, lists.length - 1);
	}

	private ListNode solve(ListNode[] lists, int left, int right) {
	 	if (left == right) {
	 		return lists[left];
		} else if (left + 1 == right) {
	 		return merge(lists[left], lists[right]);
		}

	 	int m = (left + right) / 2;

	 	return merge(solve(lists, left, m), solve(lists, m+1, right));
	}

	private ListNode merge(ListNode list1, ListNode list2) {
	 	ListNode next1 = list1;
	 	ListNode next2 = list2;

		ListNode head = new ListNode();
		ListNode cur = head;

	 	while (next1 != null && next2 != null) {
	 		if (next1.val <= next2.val) {
	 			cur.next = new ListNode(next1.val);
	 			next1 = next1.next;
			} else {
	 			cur.next = new ListNode(next2.val);
	 			next2 = next2.next;
			}

	 		cur = cur.next;
		}

	 	while (next1 != null) {
			cur.next = new ListNode(next1.val);
			next1 = next1.next;
			cur = cur.next;
		}

		while (next2 != null) {
			cur.next = new ListNode(next2.val);
			next2 = next2.next;
			cur = cur.next;
		}

		return head.next;
	}

	public static void main(String[] args) {
	}
}
