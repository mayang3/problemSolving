package hackerrank.cs.dataStructure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baejunbeom
 */
public class PostorderTraversal {
	static class Node {
		int data;
		Node left;
		Node right;
	}

	void postOrder(Node root) {
		List<Integer> ret = new ArrayList<>();

		putPostOrder(root, ret);

		for (int i=0 ; i<ret.size() ; i++) {
			System.out.print(ret.get(i));

			if (i != ret.size() - 1) {
				System.out.print(" ");
			}
		}
	}

	void putPostOrder(Node root, List<Integer> ret) {
		if (root == null) return;

		if (root.left != null)
			putPostOrder(root.left, ret);

		if (root.right != null)
			putPostOrder(root.right, ret);

		ret.add(root.data);
	}
}
