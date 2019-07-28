package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neo82
 */
public class PathSum2 {

	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> retList = new ArrayList<>();

		solve(root, 0, sum, new ArrayList<>(), retList);

		return retList;
	}

	public void solve(TreeNode node, int sum, int totalSum, List<Integer> pathList, List<List<Integer>> retList) {
		if (node == null) {
			return;
		}

		int addSum = sum + node.val;

		if (node.left == null && node.right == null) {
			if (addSum == totalSum) {
				List<Integer> list = new ArrayList<>(pathList);
				list.add(node.val);
				retList.add(list);
			}

			return;
		}

		pathList.add(node.val);

		if (node.left != null) {
			solve(node.left, addSum, totalSum, pathList, retList);
		}

		if (node.right != null) {
			solve(node.right, addSum, totalSum, pathList, retList);
		}

		pathList.remove(pathList.size()-1);
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
