package baekjoon.tree;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1991
 */
public class 트리순회 {

	static class Node {
		char data;
		Node left;
		Node right;

		Node(char data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("Node{");
			sb.append("data=").append(data);
			sb.append(", left=").append(left);
			sb.append(", right=").append(right);
			sb.append('}');
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		Node root = makeSubTree(scanner);

		for (int i = 1; i < n; i++) {
			insertNode(root, makeSubTree(scanner));
		}

		preOrder(root);
		System.out.println();

		inOrder(root);
		System.out.println();

		postOrder(root);

	}

	private static void postOrder(Node root) {
		if (root.left != null) {
			postOrder(root.left);
		}

		if (root.right != null) {
			postOrder(root.right);
		}

		System.out.print(root.data);
	}

	private static void inOrder(Node root) {

		if (root.left != null) {
			inOrder(root.left);
		}

		System.out.print(root.data);

		if (root.right != null) {
			inOrder(root.right);
		}
	}

	private static void preOrder(Node root) {
		System.out.print(root.data);

		if (root.left != null) {
			preOrder(root.left);
		}

		if (root.right != null) {
			preOrder(root.right);
		}

	}

	static void insertNode(Node root, Node insert) {
		if (root.data == insert.data) {
			root.left = insert.left;
			root.right = insert.right;
			return;
		}

		if (root.left != null) {
			insertNode(root.left, insert);
		}

		if (root.right != null) {
			insertNode(root.right, insert);
		}
	}

	static Node makeSubTree(Scanner scanner) {
		char data = scanner.next().charAt(0);
		char left = scanner.next().charAt(0);
		char right = scanner.next().charAt(0);

		return new Node(data, makeNode(left), makeNode(right));
	}

	static Node makeNode (char c) {
		return '.' == c ? null : new Node(c, null, null);
	}
}
