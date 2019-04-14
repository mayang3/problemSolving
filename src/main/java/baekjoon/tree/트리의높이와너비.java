package baekjoon.tree;

import java.util.*;

public class 트리의높이와너비 {

	static List<Integer>[] width;
	static int widthCnt = 0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		width = new List[n+1];

		for (int i = 1; i <= n; i++) {
			width[i] = new ArrayList();
		}

		Node root = makeRootNode(scanner);

		for (int i = 1; i < n; i++) {
			int data = scanner.nextInt();
			int left = scanner.nextInt();
			int right = scanner.nextInt();

			insert(root, data, left, right);
		}

		// 각 노드의 레벨과 너비를 구한다.
		inOrder(root, 1);

		int max = 0;
		int level = 1;

		// 최대 너비의 값과 level 을 구한다.
		for (int i = 1; i <= n ; i++) {
			List<Integer> widthList = width[i];
			Collections.sort(widthList);

			if (widthList.size() > 0) {
				int diff = Math.abs(widthList.get(widthList.size() - 1) - widthList.get(0) + 1);

				if (diff > max) {
					max = diff;
					level = i;
				}
			}
		}

		System.out.println(level + " " + max);
	}

	static Node makeRootNode(Scanner scanner) {
		int d = scanner.nextInt();
		int l = scanner.nextInt();
		int r = scanner.nextInt();

		Node root = new Node(d, null, null);
		insert(root, d, l, r);

		return root;
	}

	private static void inOrder(Node root, int level) {

		if (root.left != null) {
			inOrder(root.left, level+1);
		}

		width[level].add(++widthCnt);
//		System.out.println("here : " + root.data + " widthCnt : " + widthCnt);

		if (root.right != null) {
			inOrder(root.right, level+1);
		}
	}

	/**
	 * 이진트리를 만든다.
	 * 
	 * @param root
	 * @param data
	 * @param left
	 * @param right
	 */
	static void insert(Node root, int data, int left, int right) {
		if (root.data == data) {
			if (left != -1) {
				root.left = new Node(left, null, null);
			}

			if (right != -1) {
				root.right = new Node(right, null, null);
			}

			return;
		}


		if (root.left != null) {
			insert(root.left, data, left, right);
		}

		if (root.right != null) {
			insert(root.right, data, left, right);
		}
	}

	static class Node {
		int data;
		Node left;
		Node right;

		Node (int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
}
