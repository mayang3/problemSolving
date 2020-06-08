package leetcode;

import java.util.*;

public class BinaryTreeVerticalOrderTraversal {

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


	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();

		if (root == null) {
			return res;
		}

		TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();

		if (root == null) {
			return res;
		}

		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(0, root));

		while (q.isEmpty() == false) {
			Pair pair = q.poll();

			int columnLevel = pair.columnLevel;
			TreeNode treeNode = pair.treeNode;

			List<Integer> treeNodes = treeMap.computeIfAbsent(columnLevel, t -> new ArrayList<>());
			treeNodes.add(treeNode.val);

			if (treeNode.left != null) {
				q.add(new Pair(columnLevel-1, treeNode.left));
			}

			if (treeNode.right != null) {
				q.add(new Pair(columnLevel+1, treeNode.right));
			}
		}



		for (List<Integer> list : treeMap.values()) {
			res.add(list);
		}

		return res;
	}

	static class Pair {
		int columnLevel;
		TreeNode treeNode;

		public Pair(int columnLevel, TreeNode treeNode) {
			this.columnLevel = columnLevel;
			this.treeNode = treeNode;
		}
	}

	public static void main(String[] args) {
		BinaryTreeVerticalOrderTraversal traversal = new BinaryTreeVerticalOrderTraversal();
		List<List<Integer>> lists = traversal.verticalOrder(null);

		System.out.println(lists);
	}
}
