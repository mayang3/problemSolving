package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringJoiner;

/**
 * @author neo82
 */
public class MergeKSortedList {
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
	 * Input:
	 * [
	 *   1->4->5,
	 *   1->3->4,
	 *   2->6
	 * ]
	 * Output: 1->1->2->3->4->4->5->6
	 *
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		Queue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.val));

		ListNode head = new ListNode(0), node = head;

		for (ListNode ln : lists) {
			if (ln != null) {
				pq.offer(ln);
			}
		}

		while (!pq.isEmpty()) {
			node.next = pq.poll();
			node = node.next;

			if (node.next != null) {
				pq.offer(node.next);
			}
		}

		return head.next;
	}

	public static void main(String[] args) {
		ListNode l13 = new ListNode(5);
		ListNode l12 = new ListNode(4);
		l12.next = l13;
		ListNode l1 = new ListNode(1);
		l1.next = l12;

		ListNode l23 = new ListNode(4);
		ListNode l22 = new ListNode(3);
		l22.next = l23;
		ListNode l2 = new ListNode(1);
		l2.next = l22;

		ListNode l32 = new ListNode(6);
		ListNode l3 = new ListNode(2);
		l3.next = l32;

		ListNode[] lists = {l1, l2, l3};

		MergeKSortedList mergeKSortedList = new MergeKSortedList();

		System.out.println(mergeKSortedList.mergeKLists(lists));
	}
}
