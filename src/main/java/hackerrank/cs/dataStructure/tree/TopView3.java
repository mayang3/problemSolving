package hackerrank.cs.dataStructure.tree;

/**
 * @author baejunbeom
 */
public class TopView3 {

	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}

	public void topView(Node root) {
		printLeftNode(root.left);
		System.out.print(root.data);
		printRightNode(root);
	}

	private void printLeftNode(Node root) {
		if (root == null) {
			return;
		}

		if (root.left != null) {
			printLeftNode(root.left);
		}

		System.out.print(root.data + " ");
	}

	private void printRightNode(Node root) {
		if (root == null || root.right == null) {
			return;
		}

		System.out.print(" ");
		System.out.print(root.right.data);

		printRightNode(root.right);
	}

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.right = new Node(4);
		root.left.right.right = new Node(5);
		root.left.right.right.right = new Node(6);

		TopView3 topView3 = new TopView3();
		topView3.topView(root);

	}
}
