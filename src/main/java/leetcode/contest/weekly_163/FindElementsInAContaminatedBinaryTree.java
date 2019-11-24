package leetcode.contest.weekly_163;

import java.util.HashSet;
import java.util.Set;

public class FindElementsInAContaminatedBinaryTree {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	class FindElements {
		private Set<Integer> set = new HashSet<>();

		public FindElements(TreeNode root) {
			recover(root, 0);
		}

		private void recover(TreeNode root, int val) {
			if (root == null) {
				return;
			}

			root.val = val;
			set.add(val);

			recover(root.left, 2 * root.val + 1);
			recover(root.right, 2 * root.val + 2);
		}

		public boolean find(int target) {
			return set.contains(target);
		}
	}

	public static void main(String[] args) {

	}
}
