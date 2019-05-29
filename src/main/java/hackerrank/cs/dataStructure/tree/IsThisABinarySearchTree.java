package hackerrank.cs.dataStructure.tree;

/**
 * @author neo82
 */
public class IsThisABinarySearchTree {

	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	boolean checkBST(Node root) {
		return solve(root, 0, (int)1e4);
	}

	boolean solve(Node root, int min, int max) {
		if (root == null) {
			return true;
		} else if (root.data <= min || root.data >= max) {
			return false;
		}

		return solve(root.left, min, root.data) && solve(root.right, root.data, max);
	}

	public static void main(String[] args) {

		Node r2 = new Node(14, null, null);

		Node l = new Node(5, null, null);
		Node r = new Node(15, r2, null);

		Node root = new Node(10, l, r);

		IsThisABinarySearchTree tree = new IsThisABinarySearchTree();

		boolean b = tree.checkBST(root);

		System.out.println(b);
	}


}
