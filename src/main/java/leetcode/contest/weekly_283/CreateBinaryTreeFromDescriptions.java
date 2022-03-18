package leetcode.contest.weekly_283;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CreateBinaryTreeFromDescriptions {

	public class TreeNode {
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

	public TreeNode createBinaryTree(int[][] descriptions) {
		Map<Integer, TreeNode> treeMap = new HashMap<>();
		Set<Integer> childSet = new HashSet<>();

		for (int i = 0; i < descriptions.length; i++) {
			int parent = descriptions[i][0];
			int child = descriptions[i][1];
			int isLeft = descriptions[i][2];

			TreeNode p = treeMap.computeIfAbsent(parent, t -> new TreeNode(parent));
			TreeNode c = treeMap.computeIfAbsent(child, t -> new TreeNode(child));

			if (isLeft == 1) {
				p.left = c;
			} else {
				p.right = c;
			}

			childSet.add(child);
		}

		Set<Integer> allNodes = treeMap.keySet();
		allNodes.removeAll(childSet);

		return treeMap.get(allNodes.iterator().next());
	}

	public static void main(String[] args) {
		int [][] desc = {{20,15,1},{20,17,0},{50,20,1},{50,80,0},{80,19,1}};

		CreateBinaryTreeFromDescriptions createBinaryTreeFromDescriptions = new CreateBinaryTreeFromDescriptions();
		System.out.println(createBinaryTreeFromDescriptions.createBinaryTree(desc));
	}
}
