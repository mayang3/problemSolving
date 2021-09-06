package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class VerticalOrderTraversalOfaBinaryTree {

	public class TreeNode {
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

	public List<List<Integer>> verticalTraversal(TreeNode root) {
		TreeMap<Integer, TreeMap<Integer, List<Integer>>> treeMap = new TreeMap<>();

		solve(root, treeMap, 0, 0);


		List<List<Integer>> res = new ArrayList<>();
		for (TreeMap<Integer, List<Integer>> tt : treeMap.values()) {

			List<Integer> rowList = new ArrayList<>();

			for (List<Integer> list : tt.values()) {
				Collections.sort(list);
				rowList.addAll(list);
			}

			res.add(rowList);
		}

		return res;
	}

	private void solve(TreeNode root, TreeMap<Integer, TreeMap<Integer, List<Integer>>> treeMap, int row, int col) {

		treeMap.computeIfAbsent(col, t -> new TreeMap<>()).computeIfAbsent(row, t -> new ArrayList<>()).add(root.val);

		if (root.left != null) {
			solve(root.left, treeMap, row+1, col-1);
		}

		if (root.right != null) {
			solve(root.right, treeMap, row+1, col+1);
		}
	}
}
