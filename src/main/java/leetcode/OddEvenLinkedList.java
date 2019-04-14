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

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("ListNode{");
			sb.append("val=").append(val);
			sb.append(", next=").append(next);
			sb.append('}');
			return sb.toString();
		}
	}

	public ListNode oddEvenList(ListNode head) {
		if (head == null) {
			return null;
		}

		if (head.next == null) {
			return head;
		}

		ListNode currentHead = head;

		ListNode firstEvenNode = null;
		ListNode currentEvenNode = null;

		int i=1;

		while(currentHead.next != null) {
			ListNode next = currentHead.next;

			int nextIndex = i+1;

			if (nextIndex==2) {
				firstEvenNode = next;
				currentEvenNode = firstEvenNode;
				currentHead.next = next.next;
				i++;
				continue;
			} else if (nextIndex % 2 == 0) {
				currentEvenNode.next = next;
				currentEvenNode = next;
				currentHead.next = next.next;
				i++;
				continue;
			}

			currentHead = currentHead.next;
			i++;
		}

		currentEvenNode.next = null;
		// 짝수 리스트랑 연결
		currentHead.next = firstEvenNode;

		return head;
	}

	ListNode make() {
		ListNode head = new ListNode(1);
		ListNode current = head;

		for (int i=2 ; i<=5 ; i++) {
			current.next = new ListNode(i);
			current = current.next;
		}

		return head;
	}


	public static void main(String[] args) {
		OddEvenLinkedList oddEvenLinkedList = new OddEvenLinkedList();
		ListNode listNode = oddEvenLinkedList.oddEvenList(oddEvenLinkedList.make());

		System.out.println(listNode);
	}
}
