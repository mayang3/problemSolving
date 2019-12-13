package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllNodeDistanceKInBinaryTree {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
		Set<Integer>[] graph = new Set[501];

		for (int i = 0; i < graph.length; i++) {
			graph[i] = new HashSet<>();
		}

		traverse(root, graph);

		List<Integer> ret = new ArrayList<>();

		solve(ret, graph, new boolean[501], target.val, K);

		return ret;
	}

	private void solve(List<Integer> ret, Set<Integer>[] graph, boolean[] visited, int here, int k) {
		visited[here] = true;

		if (k == 0) {
			ret.add(here);
		}

		for (int next : graph[here]) {
			if (visited[next] == false) {
				solve(ret, graph, visited, next, k - 1);
			}
		}

	}

	private void traverse(TreeNode root, Set<Integer>[] graph) {
		if (root == null) {
			return;
		}

		if (root.left != null) {
			graph[root.val].add(root.left.val);
			graph[root.left.val].add(root.val);
			traverse(root.left, graph);
		}

		if (root.right != null) {
			graph[root.val].add(root.right.val);
			graph[root.right.val].add(root.val);
			traverse(root.right, graph);
		}
	}

	public static void main(String[] args) {
		TreeNode leftOfRightOfLeft = new TreeNode(7);
		TreeNode rightOfRightOfLeft = new TreeNode(4);

		TreeNode leftOfLeft = new TreeNode(6);
		TreeNode rightOfLeft = new TreeNode(2);
		rightOfLeft.left = leftOfRightOfLeft;
		rightOfLeft.right = rightOfRightOfLeft;

		TreeNode left = new TreeNode(5);
		left.left = leftOfLeft;
		left.right = rightOfLeft;

		TreeNode leftOfRight = new TreeNode(0);
		TreeNode rightOfRight = new TreeNode(8);

		TreeNode right = new TreeNode(1);
		right.left = leftOfRight;
		right.right = rightOfRight;

		TreeNode root = new TreeNode(3);
		root.left = left;
		root.right = right;

		AllNodeDistanceKInBinaryTree allNodeDistanceKInBinaryTree = new AllNodeDistanceKInBinaryTree();
		List<Integer> ret = allNodeDistanceKInBinaryTree.distanceK(root, left, 2);

		System.out.println(ret);

	}
}
