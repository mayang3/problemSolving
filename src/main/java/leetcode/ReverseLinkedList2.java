package leetcode;

// solution code
public class ReverseLinkedList2 {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null) {
			return null;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode pre = dummy;

		for (int i = 0; i < m - 1; i++) {
			pre = pre.next;
		}

		// start 는 reverse 된 맨 마지막 node
		ListNode start = pre.next;
		// then 이 이동하면서 pre.next 의 노드에 연결 (reverse) 한다.
		ListNode then = start.next;

		for (int i = 0; i < n - m; i++) {
			start.next = then.next;
			then.next = pre.next;
			pre.next = then;
			then = start.next;
		}

		return dummy.next;
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

		ReverseLinkedList2 rll = new ReverseLinkedList2();
		ListNode ret = rll.reverseBetween(listNode1, 2, 4);

		StringBuilder sb = new StringBuilder();

		while (ret != null) {
			sb.append(ret.val);
			ret = ret.next;

			if (ret != null) {
				sb.append("->");
			}
		}

		System.out.println(sb.toString());
	}
}
