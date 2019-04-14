package hackerrank.cs.dataStructure.linkedlist;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 분명히 맞게 짰는데 fail..
 * @author baejunbeom
 */
public class CycleDetection {
	static class SinglyLinkedListNode {
		public int data;
		public SinglyLinkedListNode next;

		public SinglyLinkedListNode(int nodeData) {
			this.data = nodeData;
			this.next = null;
		}
	}

	static class SinglyLinkedList {
		public SinglyLinkedListNode head;
		public SinglyLinkedListNode tail;

		public SinglyLinkedList() {
			this.head = null;
			this.tail = null;
		}

		public void insertNode(int nodeData) {
			SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

			if (this.head == null) {
				this.head = node;
			} else {
				this.tail.next = node;
			}

			this.tail = node;
		}
	}

	public static void printSinglyLinkedList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
		while (node != null) {
			bufferedWriter.write(String.valueOf(node.data));

			node = node.next;

			if (node != null) {
				bufferedWriter.write(sep);
			}
		}
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		BufferedWriter bufferedWriter = null;
		try {

			int tests = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int testsItr = 0; testsItr < tests; testsItr++) {
				int index = scanner.nextInt();
				scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

				SinglyLinkedList llist = new SinglyLinkedList();

				int llistCount = scanner.nextInt();
				scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

				for (int i = 0; i < llistCount; i++) {
					int llistItem = scanner.nextInt();
					scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

					llist.insertNode(llistItem);
				}

				boolean result = hasCycle(llist.head);

				System.out.println(result);

			}
		} finally {
			scanner.close();
		}
	}

	static boolean hasCycle(SinglyLinkedListNode head) {
		return false;
	}

}
