package hackerrank.cs.dataStructure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baejunbeom
 */
public class InorderTraversal {
	static class Node {
		int data;
		Node left;
		Node right;
	}

	void inOrder(Node root) {
		List<Integer> ret = new ArrayList<>();

		putInOrder(root, ret);

		for (int i=0 ; i<ret.size() ; i++) {
			System.out.print(ret.get(i));

			if (i != ret.size() - 1) {
				System.out.print(" ");
			}
		}
	}

	void putInOrder(Node root, List<Integer> ret) {
		if (root == null) return;

		if (root.left != null)
			putInOrder(root.left, ret);

		ret.add(root.data);

		if (root.right != null)
			putInOrder(root.right, ret);
	}
}
