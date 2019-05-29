package hackerrank.cs.dataStructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author neo82
 */
public class SwapNodesAlgo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		Node root = new Node(1);

		Queue<Node> q = new LinkedList<>();
		q.add(root);

		for (int i = 0; i < n; i++) {
			Node node = q.poll();

			int lData = scanner.nextInt();
			int rData = scanner.nextInt();

			if (lData != -1) {
				node.left = new Node(lData, null, null);
				q.add(node.left);
			}

			if (rData != -1) {
				node.right = new Node(rData, null, null);
				q.add(node.right);
			}
		}

		int t = scanner.nextInt();

		for (int i = 0; i < t; i++) {
			int k = scanner.nextInt();

			solve(k, root, 1);
			System.out.println();
		}

	}

	private static void solve(int k, Node root, int level) {

		if (level % k == 0) {
			Node temp = root.left;

			root.left = root.right;
			root.right = temp;
		}

		if (root.left != null) {
			solve(k, root.left, level+1);
		}

		System.out.print(root.data + " ");

		if (root.right != null) {
			solve(k, root.right, level+1);
		}
	}

	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		Node (int data) {
			this.data = data;
		}
	}
}
