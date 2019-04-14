package hackerrank.cs.dataStructure.tree;

/*

Complete the preOrder function in your editor below, which has  parameter: a pointer to the root of a binary tree. It must print the values in the tree's preorder traversal as a single line of space-separated values.

Input Format

Our hidden tester code passes the root node of a binary tree to your preOrder function.

Constraints

 Nodes in the tree

Output Format

Print the tree's preorder traversal as a single line of space-separated values.

Sample Input

     1
      \
       2
        \
         5
        /  \
       3    6
        \
         4
Sample Output

1 2 5 3 4 6


 */

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/tree-preorder-traversal/problem
 *
 * @author baejunbeom
 */
public class PreorderTraversal {

	static class Node {
		int data;
		Node left;
		Node right;
	}

	void preOrder(Node root) {
		List<Integer> ret = new ArrayList<>();

		putPreOrder(root, ret);

		for (int i=0 ; i<ret.size() ; i++) {
			System.out.print(ret.get(i));

			if (i != ret.size() - 1) {
				System.out.print(" ");
			}
		}
	}

	void putPreOrder(Node root, List<Integer> ret) {
		if (root == null) return;

		ret.add(root.data);

		if (root.left != null)
			putPreOrder(root.left, ret);

		if (root.right != null)
			putPreOrder(root.right, ret);
	}
}
