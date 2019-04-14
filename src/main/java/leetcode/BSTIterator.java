package leetcode;

/*

Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

*/

import java.util.Stack;

public class BSTIterator {

	private Stack<TreeNode> stack;
	public BSTIterator(TreeNode root) {
		stack = new Stack<>();
		TreeNode cur = root;
		while(cur != null){
			stack.push(cur);
			if(cur.left != null)
				cur = cur.left;
			else
				break;
		}
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	/** @return the next smallest number */
	public int next() {
		TreeNode node = stack.pop();
		TreeNode cur = node;

		if (cur.right != null) {

			cur = cur.right;

			while(cur != null) {

				stack.push(cur);

				if(cur.left != null)
					cur = cur.left;
				else
					break;

			}
		}
		return node.val;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(50);
		root.left = new TreeNode(30);
		root.right = new TreeNode(80);

		root.left.left = new TreeNode(20);
		root.left.right = new TreeNode(40);

		root.right.left = new TreeNode(70);
		root.right.right = new TreeNode(90);

		BSTIterator iterator = new BSTIterator(root);

		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
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