package hackerrank.cs.dataStructure.tree;

import java.util.Scanner;

/**
 * {@link SelfBalancingTree_Solution} 과 비교해볼것.. 맞게 푼것 같은데 오류나넹..
 */
public class SelfBalancingTree {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int rootVal = scanner.nextInt();

		root = new Node();
		root.val = rootVal;
		root.ht = 1;

		for (int i = 1; i < n; i++) {
			insert(root, scanner.nextInt());
		}

//		preOrder(root);
	}

	static class Node {
		int val;
		int ht;
		Node left;
		Node right;
	}

	static Node root;

	static int getHeight(Node node) {
		if (node == null) {
			return 0;
		}

		return node.ht;
	}

	static int getBalanceFactor(Node node) {
		if (node == null) {
			return 0;
		}

		return getHeight(node.left) - getHeight(node.right);
	}

	static Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;

		x.right = y;
		y.left = T2;

		y.ht = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
		x.ht = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

		return x;
	}

	static Node leftRotate(Node y) {
		Node x = y.right;
		Node T2 = x.left;

		x.left = y;
		y.right = T2;

		y.ht = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
		x.ht = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

		return x;
	}

	static Node insert(Node node, int key) {
		// 1. BST insertion
		if (node == null) {
			Node newNode = new Node();
			newNode.val = key;
			newNode.ht = 1;
			return newNode;
		} else if (key <= node.val) {
			node.left = insert(node.left, key);
		} else if (key > node.val) {
			node.right = insert(node.right, key);
		} else {
			return node;
		}

		// 2. update height
		node.ht = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

		// 3. get balance factor
		int balance = getBalanceFactor(node);

		// 4. rotation
		// 4-1. Left Left Case
		if (balance > 1 && key < node.left.val) {
			return rightRotate(node);
		}

		// 4-2. Left Right Case
		if (balance > 1 && key > node.left.val) {
			leftRotate(node.left);
			return rightRotate(node);
		}

		// 4-3. Right Right Case
		if (balance < -1 && key > node.right.val) {
			return leftRotate(node);
		}

		// 4-4. Right Left Case
		if (balance < -1 && key < node.right.val) {
			rightRotate(node.right);
			return leftRotate(node);
		}

		return node;
	}

	static void preOrder(Node root) {
		if (root == null) {
			return;
		}

		System.out.print(root.val + " ");

		preOrder(root.left);
		preOrder(root.right);
	}
}
