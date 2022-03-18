package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathSum3 {
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

	int cnt = 0;
	public int pathSum(TreeNode root, int targetSum) {
		solve(root, targetSum);
		return cnt;
	}

	List<Integer> solve(TreeNode root, int targetSum) {
		if (root == null) {
			return null;
		}

		List<Integer> res = new ArrayList<>();
		res.add(root.val);

		if (root.val == targetSum) {
			cnt++;
		}

		if (root.left != null) {
			for (int val : solve(root.left, targetSum)) {
				int sum = val + root.val;

				if (sum == targetSum) {
					cnt++;
				}

				res.add(sum);
			}
		}

		if (root.right != null) {
			for (int val : solve(root.right, targetSum)) {
				int sum = val + root.val;

				if (sum == targetSum) {
					cnt++;
				}

				res.add(sum);
			}
		}

		return res;
	}

	public static void main(String[] args) {

		TreeNode leftRight = new TreeNode(2, null, new TreeNode(1));
		TreeNode leftLeft = new TreeNode(3, new TreeNode(3), new TreeNode(-2));

		TreeNode rightRight = new TreeNode(11, null, null);

		TreeNode right = new TreeNode(-3, null, rightRight);
		TreeNode left = new TreeNode(5, leftLeft, leftRight);

		TreeNode root = new TreeNode(10, left, right);

	  PathSum3 pathSum3 = new PathSum3();
		System.out.println(pathSum3.pathSum(root, 8));
	}
}
