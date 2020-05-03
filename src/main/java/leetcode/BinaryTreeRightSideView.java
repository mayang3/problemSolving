package leetcode;

import java.util.*;

public class BinaryTreeRightSideView {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> ret = new ArrayList();

		Deque<TreeNode> q = new LinkedList<>();
		q.add(root);
		ret.add(root.val);

		while (q.isEmpty() == false) {

			int size =  Integer.valueOf(q.size());

			// 해당 level 을 모두 넣는다.
			for (int i = 0; i < size; i++) {
				TreeNode poll = q.poll();

				if (poll.left != null) {
					q.add(poll.left);
				}

				if (poll.right != null) {
					q.add(poll.right);
				}
			}

			if (q.isEmpty()) {
				break;
			}

			// 해당 level 의 iteration 이 끝나면, 가장 오른쪽에 있는 녀석을 peek 해서 list 에 넣는다.
			ret.add(q.peekLast().val);
		}

		return ret;
	}
}
