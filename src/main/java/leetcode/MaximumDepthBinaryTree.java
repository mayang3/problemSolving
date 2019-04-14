package leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author baejunbeom
 */
public class MaximumDepthBinaryTree {

	public int maxDepth(TreeNode root) {
		if(root==null){
			return 0;
		}

		int left = maxDepth(root.left);
		int right = maxDepth(root.right);

		return 1 + Math.max(left, right);
	}

//	public int maxDepth(TreeNode root) {
//		if (root == null) {
//			return 0;
//		}
//
//		Stack<TreeNode> stack = new Stack<>();
//		Set<TreeNode> visitSet = new HashSet<>();
//
//		stack.push(root);
//
//		int depth = 1;
//		int max = 0;
//
//		while (!stack.isEmpty()) {
//			TreeNode peek = stack.peek();
//
//			TreeNode adjacent = getAdjacent(peek, visitSet);
//
//			if (adjacent == null) {
//				max = Math.max(depth, max);
//				depth=depth-1;
//				stack.pop();
//				continue;
//			}
//
//			depth++;
//			visitSet.add(adjacent);
//			stack.push(adjacent);
//
//		}
//
//		return max;
//	}
//
//	private TreeNode getAdjacent(TreeNode peek, Set<TreeNode> visitSet) {
//		if (peek.left != null && !visitSet.contains(peek.left)) {
//			return peek.left;
//		}
//
//		if (peek.right != null && !visitSet.contains(peek.right)) {
//			return peek.right;
//		}
//
//		return null;
//	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			TreeNode treeNode = (TreeNode)o;

			if (val != treeNode.val) {
				return false;
			}
			if (left != null ? !left.equals(treeNode.left) : treeNode.left != null) {
				return false;
			}
			return right != null ? right.equals(treeNode.right) : treeNode.right == null;
		}

		@Override
		public int hashCode() {
			int result = val;
			result = 31 * result + (left != null ? left.hashCode() : 0);
			result = 31 * result + (right != null ? right.hashCode() : 0);
			return result;
		}
	}

 	public static TreeNode makeTreeNode() {
		TreeNode rootNode = new TreeNode(50);
		rootNode.left = new TreeNode(10);
		rootNode.right = new TreeNode(70);

		rootNode.left.left = new TreeNode(5);
		rootNode.left.left.left = new TreeNode(2);

		rootNode.right.right = new TreeNode(74);
		rootNode.right.right.right = new TreeNode(78);
		rootNode.right.right.left = new TreeNode(80);
		rootNode.right.right.left.right = new TreeNode(88);

		return rootNode;
	}

	public static void main(String[] args) {
		MaximumDepthBinaryTree maximumDepthBinaryTree = new MaximumDepthBinaryTree();
		int i = maximumDepthBinaryTree.maxDepth(makeTreeNode());

		System.out.println(i);
	}
}
