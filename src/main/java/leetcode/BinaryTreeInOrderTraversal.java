package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neo82
 */
public class BinaryTreeInOrderTraversal {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> ret = new ArrayList<>();

		solve(root, ret);

		return ret;
	}


	public void solve(TreeNode root, List<Integer> list) {
		if (root == null) {
			return;
		}

		if (root.left != null) {
			solve(root.left, list);
		}

		list.add(root.val);

		if (root.right != null) {
			solve(root.right, list);
		}

	}

	public static void main(String[] args) {
		BinaryTreeInOrderTraversal tree = new BinaryTreeInOrderTraversal();

		tree.inorderTraversal(null);
	}
}
