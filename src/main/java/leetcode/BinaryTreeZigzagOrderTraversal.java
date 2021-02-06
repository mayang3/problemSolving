package leetcode;

import java.util.*;

public class BinaryTreeZigzagOrderTraversal {
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();

		if (root == null) {
			return res;
		}

		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		int level = 1;

		while (q.isEmpty() == false) {
			int size = Integer.valueOf(q.size());

			List<Integer> curVals = new ArrayList<>();

			for (int i = 0; i < size; i++) {
				TreeNode poll = q.poll();
				curVals.add(poll.val);

				if (poll.left != null) {
					q.add(poll.left);
				}

				if (poll.right != null) {
					q.add(poll.right);
				}
			}

			if (level % 2 == 0) {
				Collections.reverse(curVals);
			}

			res.add(curVals);
			level++;
		}

		return res;
	}

	public static void main(String[] args) {
		TreeNode leftLeft = new TreeNode(4);
		TreeNode rightRight = new TreeNode(5);

		TreeNode left = new TreeNode(2, leftLeft, null);
		TreeNode right = new TreeNode(3, null, rightRight);

		TreeNode root = new TreeNode(1, left, right);

		BinaryTreeZigzagOrderTraversal binaryTreeZigzagOrderTraversal = new BinaryTreeZigzagOrderTraversal();
		System.out.println(binaryTreeZigzagOrderTraversal.zigzagLevelOrder(root));
	}

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
}
