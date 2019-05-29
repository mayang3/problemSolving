package hackerrank.cs.dataStructure.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * @author neo82
 */
public class CycleDetection {
	static class SinglyLinkedListNode {
		int data;
		SinglyLinkedListNode next;

		SinglyLinkedListNode(int data, SinglyLinkedListNode next) {
			this.data = data;
			this.next = next;
		}
	}

	static boolean hasCycle(SinglyLinkedListNode head) {
		if (head == null) {
			return false;
		}

		Set<SinglyLinkedListNode> set = new HashSet<>();

		while (head != null) {
			if (set.contains(head)) {
				return true;
			}

			set.add(head);

			head = head.next;
		}

		return false;
	}

	public static void main(String[] args) {
		SinglyLinkedListNode head = new SinglyLinkedListNode(1, null);

		SinglyLinkedListNode node6 = new SinglyLinkedListNode(3, head);
		SinglyLinkedListNode node5 = new SinglyLinkedListNode(2, node6);
		SinglyLinkedListNode node4 = new SinglyLinkedListNode(1, node5);
		SinglyLinkedListNode node3 = new SinglyLinkedListNode(3, node4);
		SinglyLinkedListNode node2 = new SinglyLinkedListNode(1, node3);

		head.next = node2;

		System.out.println(hasCycle(head));


	}
}
