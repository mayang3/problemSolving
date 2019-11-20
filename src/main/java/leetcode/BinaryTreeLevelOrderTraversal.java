package leetcode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BinaryTreeLevelOrderTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public List<List<Integer>> levelOrder(TreeNode root) {
		Map<Integer, List<Integer>> map = new LinkedHashMap<>();

		solve(map, root, 0);

		List<List<Integer>> returnList = new ArrayList<>();

		for (List<Integer> list : map.values()) {
			returnList.add(list);
		}

		return returnList;
	}

	private void solve(Map<Integer, List<Integer>> map, TreeNode root, int level) {
		if (root == null) {
			return;
		}

		List<Integer> list = map.computeIfAbsent(level, (dummy) -> new ArrayList<>());
		list.add(root.val);

		if (root.left != null) {
			solve(map, root.left, level + 1);
		} else if (root.right != null) {
			solve(map, root.right, level + 1);
		}
	}

	public static void main(String[] args) {

	}

}
