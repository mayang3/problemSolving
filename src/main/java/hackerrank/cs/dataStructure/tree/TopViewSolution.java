package hackerrank.cs.dataStructure.tree;

/**
 * @author baejunbeom
 */
public class TopViewSolution {

	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}

	void goLeft(Node node) {
		if(node.left != null) {
			goLeft(node.left);
		}
		System.out.print(node.data + " ");
	}

	void goRight(Node node) {
		System.out.print(node.data + " ");

		if(node.right != null) {
			goRight(node.right);
		}
	}

	void top_view(Node root) {
		if(root.left != null) {
			goLeft(root.left);
		}

		System.out.print(root.data + " ");

		if(root.right != null) {
			goRight(root.right);
		}
	}
}
