package hackerrank.cs.dataStructure.tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author baejunbeom
 */
public class TopView2 {

	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}

	static class QItem {
		int hd;
		Node node;

		QItem(Node root, int hp) {
			this.node = root;
			this.hd = hp;
		}
	}

	public void topView(Node root) {
		// base case
		if (root == null) {  return;  }

		// Creates an empty hashset
		HashSet<Integer> set = new HashSet<>();

		// Create a queue and add root to it
		Queue<QItem> Q = new LinkedList<QItem>();
		Q.add(new QItem(root, 0)); // Horizontal distance of root is 0

		// Standard BFS or level order traversal loop
		while (!Q.isEmpty())
		{
			// Remove the front item and get its details
			QItem qi = Q.remove();
			int hd = qi.hd;
			Node n = qi.node;

			// If this is the first node at its horizontal distance,
			// then this node is in top view
			if (!set.contains(hd))
			{
				set.add(hd);
				System.out.print(n.data + " ");
			}

			// Enqueue left and right children of current node
			if (n.left != null)
				Q.add(new QItem(n.left, hd-1));
			if (n.right != null)
				Q.add(new QItem(n.right, hd+1));
		}
	}

	public static void main(String[] args) {
	/* Create following Binary Tree
		 1
	   /  \
	  2    3
	   \
		4
		 \
		  5
		   \
			6*/
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.right = new Node(4);
		root.left.right.right = new Node(5);
		root.left.right.right.right = new Node(6);

		TopView2 topView2 = new TopView2();
		topView2.topView(root);
	}
}
