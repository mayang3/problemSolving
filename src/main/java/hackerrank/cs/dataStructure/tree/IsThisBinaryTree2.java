package hackerrank.cs.dataStructure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baejunbeom
 */
public class IsThisBinaryTree2 {

	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}

	boolean checkBST(Node root) {
		List<Integer> inOrderList = new ArrayList<>();

		try {
			inOrder(root, inOrderList);

			return true;

		} catch (Exception e) {
		}

		return false;
	}


	void inOrder(Node root, List<Integer> inOrderList) {
		if (root == null) {
			return;
		}

		if (root.left != null) {
			inOrder(root.left, inOrderList);
		}

		if (!inOrderList.isEmpty()) {
			if (root.data <= inOrderList.get(inOrderList.size() - 1)) {
				throw new RuntimeException();
			}
		}

		inOrderList.add(root.data);

		if (root.right != null) {
			inOrder(root.right, inOrderList);
		}
	}


	public static void main(String[] args) {
//		Node root = new Node(18);
//
//		root.left = new Node(8);
//		root.right = new Node(20);
//
//		root.right.left = new Node(10);
//		root.right.right = new Node(30);

		Node root = new Node(3);

		root.left = new Node(5);
		root.right = new Node(2);

		root.left.left = new Node(1);
		root.left.right = new Node(4);

		root.right.left = new Node(6);

		System.out.println(new IsThisBinaryTree2().checkBST(root));
	}
}
