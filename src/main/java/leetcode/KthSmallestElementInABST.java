package leetcode;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestElementInABST {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public int kthSmallest(TreeNode root, int k) {
		List<Integer> numberList = new ArrayList<>();
		solve(numberList, root, k);
		return numberList.get(numberList.size()-1);
	}

	public void solve(List<Integer> numberList, TreeNode root, int k) {
		if (root.left == null && root.right == null) {
			numberList.add(root.val);
			return;
		}

		if (numberList.size() == k) {
			return;
		}

		if (root.left != null) {
			solve(numberList, root.left, k);
		}

		if (numberList.size() == k) {
			return;
		}

		numberList.add(root.val);

		if (numberList.size() == k) {
			return;
		}

		if (root.right != null) {
			solve(numberList, root.right, k);
		}
	}


	public static void main(String[] args) {

		TreeNode leftRight = new TreeNode(2);

		TreeNode left = new TreeNode(1);
		left.right = leftRight;

		TreeNode right = new TreeNode(4);

		TreeNode root = new TreeNode(3);
		root.left = left;
		root.right = right;

		KthSmallestElementInABST abst = new KthSmallestElementInABST();
		int res = abst.kthSmallest(root, 1);

		System.out.println(res);
	}
}
