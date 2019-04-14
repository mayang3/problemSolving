package hackerrank.cs.dataStructure.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author baejunbeom
 */
public class LevelOrder {

	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}

	/**
	 * expected : 1 2 5 3 6 4
	 * @param root
	 */
	public void levelOrder(Node root) {

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			Node node = queue.poll();

			System.out.print(node.data + " ");

			if (node.left != null) {
				queue.add(node.left);
			}

			if (node.right != null) {
				queue.add(node.right);
			}

		}
	}

	public static void main(String[] args) {
		Node root = new Node(1);
		root.right = new Node(2);
		root.right.right = new Node(5);
		root.right.right.left = new Node(3);
		root.right.right.left.right = new Node(4);
		root.right.right.right = new Node(6);

		LevelOrder levelOrder = new LevelOrder();
		levelOrder.levelOrder(root);
	}
}
