package leetcode;

public class MinimumDepthOfBinaryTree {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public int minDepth(TreeNode root) {
		return solve(root, 1);
	}

	private int solve(TreeNode root, int depth) {
		if (root.left == null && root.right == null) {
			return depth;
		}

		int min = Integer.MAX_VALUE;

		if (root.left != null) {
			min = Math.min(min, solve(root.left, depth+1));
		}

		if (root.right != null) {
			min = Math.min(min,solve(root.right,depth+1));
		}

		return min;
	}

	public static void main(String[] args) {
		MinimumDepthOfBinaryTree tree = new MinimumDepthOfBinaryTree();
		int solve = tree.solve(null, 3);

		System.out.println(solve);
	}
}
