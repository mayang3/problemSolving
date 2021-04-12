package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BoundaryOfBinaryTree {
  public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

	public List<Integer> boundaryOfBinaryTree(TreeNode root) {
		List<Integer> left = new ArrayList<>();
		LinkedList<Integer> right = new LinkedList<>();
		List<Integer> leaves = new ArrayList<>();

		solve(root, left, right, leaves, null, true);

		left.addAll(leaves);
		left.addAll(right);

		return left;
	}

	void solve(TreeNode root, List<Integer> left, LinkedList<Integer> right, List<Integer> leaves, Boolean isLeft, boolean isRoot) {
  		if (root == null) {
  			return;
		}


  		if (root.left == null && root.right == null) {
  			leaves.add(root.val);
  			return;
		}

  		if (isLeft == null) {
  			if (isRoot) {
  				left.add(root.val);
			}

			if (root.left != null) {
				solve(root.left, left, right, leaves, isRoot ? true : null, false);
			}

			if (root.right != null) {
				solve(root.right, left, right, leaves, isRoot ? false : null, false);
			}
		} else if (isLeft) {
  			left.add(root.val);
  			if (root.left != null) {
  				solve(root.left, left, right, leaves, true, false);
			}

  			if (root.right != null) {
  				solve(root.right, left, right, leaves, root.left != null ? null : true, false);
			}
		} else if (isLeft == false) {
  			right.addFirst(root.val);

			if (root.left != null) {
				solve(root.left, left, right, leaves, root.right != null ? null : false, false);
			}

			if (root.right != null) {
  				solve(root.right, left, right, leaves, false, false);
			}
		}
	}

	public static void main(String[] args) {

	}
}
