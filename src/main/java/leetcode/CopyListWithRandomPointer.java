package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author neo82
 */
public class CopyListWithRandomPointer {
	static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}

	public Node copyRandomList(Node head) {
		if (head == null) {
			return null;
		}

		// original list map
		Map<Node, Integer> map = new HashMap<>();
		map.put(head, 0);

		List<Node> newNodeList = new ArrayList<>();

		Node cur = head.next;
		Node newHead = copy(head);
		Node newCur = newHead;

		newNodeList.add(newCur);

		int i = 1;

		while (cur != null) {
			// make original node index
			map.put(cur, i++);

			Node newNode = copy(cur);

			// make new Node;
			newCur.next = newNode;
			newCur = newCur.next;
			cur = cur.next;

			newNodeList.add(newCur);
		}

		cur = head;
		newCur = newHead;

		// link both cur node and new node
		while (cur != null) {
			Integer randomIndex = map.get(cur.random);

			if (randomIndex != null) {
				newCur.random = newNodeList.get(randomIndex);
			}

			cur = cur.next;
			newCur = newCur.next;
		}

		return newHead;
	}

	private Node copy(Node old) {
		return new Node(old.val);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node node1 = new Node(1);
		Node node2 = new Node(10);
		Node node3 = new Node(11);
		Node node4 = new Node(13);
		Node node5 = new Node(7);

		node1.random = node5;

		node2.next = node1;
		node2.random = node3;


		node3.next = node2;
		node3.random = node1;

		node4.next = node3;
		node4.random = node5;

		node5.next = node4;

		CopyListWithRandomPointer rp = new CopyListWithRandomPointer();
		Node res = rp.copyRandomList(node5);

		System.out.println(res);
	}
}
