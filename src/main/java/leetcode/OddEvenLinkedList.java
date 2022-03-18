package leetcode;

/**
 * @author baejunbeom
 */
public class OddEvenLinkedList {

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode oddEvenList(ListNode head) {
		if (head == null) {
			return null;
		} else if (head.next == null) {
			return head;
		}

		ListNode odd = head;
		ListNode even = head.next;
		ListNode evenHead = even;
		ListNode cur = even.next;
		boolean isOdd = true;

		while (cur != null) {
			if (isOdd) {
				odd.next = cur;
				odd = odd.next;
				even.next = null;
			} else {
				even.next = cur;
				even = even.next;
				odd.next = null;
			}

			cur = cur.next;
			isOdd = !isOdd;
		}

		odd.next = evenHead;

		return head;
	}

	ListNode make() {
		ListNode head = new ListNode(1);
		ListNode current = head;

		for (int i=2 ; i<=2 ; i++) {
			current.next = new ListNode(i);
			current = current.next;
		}

		return head;
	}


	public static void main(String[] args) {
		OddEvenLinkedList oddEvenLinkedList = new OddEvenLinkedList();
		ListNode listNode = oddEvenLinkedList.oddEvenList(oddEvenLinkedList.make());

		while (listNode != null) {
			System.out.println(listNode.val);
			listNode = listNode.next;
		}
	}
}
