package hackerrank.cs.dataStructure.tree;

/*

For the purposes of this challenge, we define a binary tree to be a binary search tree with the following ordering requirements:

The  value of every node in a node's left subtree is less than the data value of that node.
The  value of every node in a node's right subtree is greater than the data value of that node.
Given the root node of a binary tree, can you determine if it's also a binary search tree?

Complete the function in your editor below, which has  parameter: a pointer to the root of a binary tree. It must return a boolean denoting whether or not the binary tree is a binary search tree. You may have to write one or more helper functions to complete this challenge.

Input Format

You are not responsible for reading any input from stdin. Hidden code stubs will assemble a binary tree and pass its root node to your function as an argument.

Constraints

Output Format

You are not responsible for printing any output to stdout. Your function must return true if the tree is a binary search tree; otherwise, it must return false. Hidden code stubs will print this result as a Yes or No answer on a new line.

Sample Input

tree

Sample Output

No

 */

/**
 * @author baejunbeom
 */
public class IsThisBinaryTree {

	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}

	boolean checkBST(Node root) {
		if (root == null) {
			return true;
		}

		if (root.data < 0 || root.data > Integer.MAX_VALUE) {
			return false;
		}

		// 1. base case : left, right child 가 null 일 수 있는 조건은 BST 에서 성립하는 조건이다.
		if (root.left == null && root.right == null) {
			return true;
		}

		boolean ret = true;

		if (root.left != null) {
			if (root.left.data >= root.data) {
				ret = false;
			} else {
				ret = ret && checkBST(root.left);
			}
		}

		if (root.right != null) {
			if (root.right.data <= root.data) {
				ret = false;
			} else {
				ret = ret && checkBST(root.right);
			}
		}

		return ret;
	}

	public static void main(String[] args) {
		Node root = new Node(2);

		root.left = new Node(1);
		root.right = new Node(4);

		root.right.left = new Node(3);
		root.right.right = new Node(5);

		root.right.left.left = new Node(6);
		root.right.left.right = new Node(7);

		IsThisBinaryTree isThisBinaryTree = new IsThisBinaryTree();
		boolean r = isThisBinaryTree.checkBST(root);

		System.out.println(r ? "Yes" : "No");
	}

}
