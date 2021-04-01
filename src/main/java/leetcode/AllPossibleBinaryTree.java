package leetcode;

import java.util.*;

public class AllPossibleBinaryTree {

	public static void main(String[] args) {
		AllPossibleBinaryTree allPossibleBinaryTree = new AllPossibleBinaryTree();
		allPossibleBinaryTree.allPossibleFBT(20);
	}

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

	Map<Integer, List<TreeNode>> memo = new HashMap();

	public List<TreeNode> allPossibleFBT(int N) {
		if (N % 2 == 0) {
			return new ArrayList<>();
		}

		if (!memo.containsKey(N)) {
			List<TreeNode> ans = new LinkedList();
			if (N == 1) {
				ans.add(new TreeNode(0));
			} else {
				for (int x = 1; x < N; x+=2) {
					for (TreeNode left: allPossibleFBT(x))
						for (TreeNode right: allPossibleFBT(N - x - 1)) {
							TreeNode root = new TreeNode(0);
							root.left = left;
							root.right = right;
							ans.add(root);
						}
				}
			}
			memo.put(N, ans);
		}

		return memo.get(N);
	}
}
