package leetcode;

public class ValidateBinarySearchTree {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public boolean isValidBST(TreeNode root) {
		MinMax left = null;
		MinMax right = null;

		if (root.left != null) {
			left = solve(root.left, true);
		}

		if (root.right != null) {
			right = solve(root.right, false);
		}

		if (left != null && left.max >= root.val) {
			return false;
		}

		if (right != null && right.min <= root.val) {
			return false;
		}

		return true;
	}

	static class MinMax {
		int min;
		int max;

		public MinMax(int min, int max) {
			this.min = min;
			this.max = max;
		}
	}

	private MinMax solve(TreeNode root, boolean isLeft) {
		if (root.left == null && root.right == null) {
			return new MinMax(root.val, root.val);
		}

		MinMax left = null;
		MinMax right = null;

		if (root.left != null) {
			left = solve(root.left, true);
		}

		if (root.right != null) {
			right = solve(root.right, false);
		}

		if ((left != null && left.max >= root.val) || (right != null && right.min <= root.val)) {
			return isLeft ? new MinMax(Integer.MAX_VALUE, Integer.MAX_VALUE) : new MinMax(Integer.MIN_VALUE, Integer.MIN_VALUE);
		}

		int min = root.val;
		int max = root.val;

		if (left != null) {
			min = Math.min(min, left.min);
			max = Math.max(max, left.max);
		}

		if (right != null) {
			min = Math.min(min, right.min);
			max = Math.max(max, right.max);
		}

		return new MinMax(min, max);
	}

	public static void main(String[] args) {

	}
}
