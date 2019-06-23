package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author neo82
 */
public class AverageOfLevelsInBinaryTree {
	  public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	  }

	public List<Double> averageOfLevels(TreeNode root) {
		List<Double> ret = new ArrayList();

		Queue<TreeNode> q = new LinkedList();
		q.add(root);

		while (q.isEmpty() == false) {
			int size = new Integer(q.size());
			double sum = 0;

			for (int i=0 ; i<size ; i++) {
				TreeNode node = q.poll();

				if (node.left != null) {
					q.add(node.left);
				}

				if (node.right != null) {
					q.add(node.right);
				}

				sum += node.val;
			}

			ret.add(sum / size);
		}

		return ret;
	}
}
