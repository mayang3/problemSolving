package leetcode;

import java.util.StringJoiner;

/**
 * @author neo82
 */
public class MergeTwoSortedList {

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}

		@Override
		public String toString() {
			return new StringJoiner(", ", ListNode.class.getSimpleName() + "[", "]").add("val=" + val).add("next=" + next).toString();
		}
	}

	/**
	 * Input: 1->2->4, 1->3->4
	 * Output: 1->1->2->3->4->4
	 *
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy1 = new ListNode(Integer.MIN_VALUE);
		ListNode dummy2 = new ListNode(Integer.MIN_VALUE);
		ListNode head = dummy1;

		dummy1.next = l1;
		dummy2.next = l2;

		while (dummy1.next != null && dummy2.next != null) {
			if (dummy1.next.val <= dummy2.next.val) {
				dummy1 = dummy1.next;
			} else {
				ListNode temp = dummy1.next;
				dummy1.next = dummy2.next;
				dummy2.next = temp;
			}
		}

		while (dummy2.next != null) {
			dummy1.next = dummy2.next;

			dummy1 = dummy1.next;
			dummy2 = dummy2.next;
		}

		return head.next;
	}

	public static void main(String[] args) {
		ListNode l12 = new ListNode(3);
		ListNode l11 = new ListNode(-9);
		l11.next = l12;

		ListNode l22 = new ListNode(7);
		ListNode l21 = new ListNode(5);
		l21.next = l22;

		System.out.println(new MergeTwoSortedList().mergeTwoLists(l11, l21));
	}

}
