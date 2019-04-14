package hackerrank.cs.dataStructure.tree;

/**
 * @author baejunbeom
 */
public class Insertion {

	/**
	 *
	 */
	static class Node {
		int data;
		Node left;
		Node right;

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

	static Node Insert(Node root, int value) {
		if (root == null) {
			Node newRoot = new Node();
			newRoot.data = value;
			return newRoot;
		}

		recursiveInsert(root, value);

		return root;
	}

	static void recursiveInsert(Node node, int value) {
		if (node.data > value) {
			if (node.left == null) {
				Node newLeftNode = new Node();
				newLeftNode.data = value;
				node.left = newLeftNode;
				return;
			}

			node = node.left;

		} else if (node.data < value) {
			if (node.right == null) {
				Node newRightNode = new Node();
				newRightNode.data = value;
				node.right = newRightNode;
				return;
			}

			node = node.right;
		}

		recursiveInsert(node, value);
	}

	public static void main(String[] args) {
		Node root = new Node();
		root.data = 4;

		Insert(root, 2);
		Insert(root, 7);
		Insert(root, 1);
		Insert(root, 3);
		Node insert = Insert(root, 6);

		System.out.println(insert);
	}
}
