package leetcode;

public class SameTree {

	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}

		if (p == null || q == null) {
			return false;
		}

		if (p.val != q.val) {
			return false;
		}

		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	public static void main(String[] args) {
		TreeNode p = makePTreeNode();
		TreeNode q = makeQTreeNode();

		boolean same = new SameTree().isSameTree(p, q);

		System.out.println(same);
	}

	private static TreeNode makeQTreeNode() {
		TreeNode treeNode = new TreeNode(10);
		treeNode.left = new TreeNode(1);
		treeNode.right = new TreeNode(20);

		return treeNode;
	}

	private static TreeNode makePTreeNode() {
		TreeNode treeNode = new TreeNode(10);
		treeNode.left = new TreeNode(1);
		treeNode.right = new TreeNode(20);

		return treeNode;
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
