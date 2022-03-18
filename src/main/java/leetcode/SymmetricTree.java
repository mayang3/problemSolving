package leetcode;

import java.util.*;

public class SymmetricTree {
	public boolean isSymmetric(TreeNode root) {
		int level = 1;

		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		while (q.isEmpty() == false) {
			int size = Integer.valueOf(q.size());
			int invalidCount = 0;

			List<Integer> left = new ArrayList<>();
			List<Integer> right = new ArrayList<>();

			for (int i = 0; i < size; i++) {
				TreeNode here = q.poll();

				if (i < (level / 2)) {
					left.add(here.val);
				} else {
					right.add(here.val);
				}

				if (here.val < -100) {
					invalidCount++;
				}

				if (here.left == null) {
					q.add(new TreeNode(-101));
				} else {
					q.add(here.left);
				}

				// right insert
				if (here.right == null) {
					q.add(new TreeNode(-101));
				} else {
					q.add(here.right);
				}
			}

			if (level > 1 && left.size() != right.size()) {
				return false;
			}

			if (isEqual(left, right) == false) {
				return false;
			}

			if (invalidCount == level) {
				break;
			}

			level *= 2;
		}

		return true;
	}

	private boolean isEqual(List<Integer> left, List<Integer> right) {
		int j = right.size()-1;

		for (int i = 0; i < left.size(); i++) {
			if (left.get(i) != right.get(j)) {
				return false;
			}

			j--;
		}

		return true;
	}

	public static void main(String[] args) {

		TreeNode root6 = new TreeNode(3);

		TreeNode root5 = new TreeNode(3);

		TreeNode root3 = new TreeNode(2, root6, null);
		TreeNode root2 = new TreeNode(2, null, root5);
		TreeNode root = new TreeNode(1, root2, root3);

		SymmetricTree symmetricTree = new SymmetricTree();
		System.out.println(symmetricTree.isSymmetric(root));
	}
}
