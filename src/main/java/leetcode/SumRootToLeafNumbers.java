package leetcode;

/**
 * @author neo82
 */
public class SumRootToLeafNumbers {
	public int sumNumbers(TreeNode root) {
		return solve(root, "");
	}

	public int solve(TreeNode node, String s) {
		if (node == null) {
			return 0;
		}

		if (node.left == null && node.right == null) {
			return Integer.parseInt(s + node.val);
		}

		int sum = 0;

		if (node.left != null) {
			sum += solve(node.left, s + node.val);
		}

		if (node.right != null) {
			sum += solve(node.right, s + node.val);
		}

		return sum;
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
