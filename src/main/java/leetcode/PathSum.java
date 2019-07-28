package leetcode;

/**
 * Accepted
 * @author neo82
 */
public class PathSum {

	public boolean hasPathSum(TreeNode root, int sum) {
		return solve(root, 0, sum);
	}

	public boolean solve(TreeNode node, int sum, int totalSum) {
		if (node == null) {
			return false;
		}

		int addSum = sum + node.val;

		if (node.left == null && node.right == null) {
			return addSum == totalSum;
		}


		if (node.left != null && solve(node.left, addSum, totalSum)) {
			return true;
		}

		if (node.right != null) {
			return solve(node.right, addSum, totalSum);
		}

		return false;
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
