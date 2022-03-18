package leetcode;

import java.util.HashSet;
import java.util.Set;

public class FindElementsInAContaminatedBinaryTree {
	static class FindElements {
		Set<Integer> valueSet = new HashSet<>();

		public FindElements(TreeNode root) {
			root.val = 0;
			flatten(root);
		}

		public boolean find(int target) {
			return valueSet.contains(target);
		}

		void flatten(TreeNode treeNode) {
			if (treeNode.left == null && treeNode.right == null) {
				return;
			}

			if (treeNode.left != null) {
				treeNode.left.val = treeNode.val * 2 + 1;
				flatten(treeNode.left);
			}

			if (treeNode.right != null) {
				treeNode.right.val = treeNode.val * 2 + 2;
				flatten(treeNode.right);
			}
		}
	}

	public static void main(String[] args) {

	}

}
