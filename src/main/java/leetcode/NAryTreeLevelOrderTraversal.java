package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NAryTreeLevelOrderTraversal {
	static class Node {
		public int val;
		public List<Node> children;

		public Node() {}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}
	}

	public List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> res = new ArrayList<>();

		Queue<Node> q = new LinkedList<>();
		q.add(root);

		while (q.isEmpty() == false) {
			int size = Integer.valueOf(q.size());

			List<Integer> nodeList = new ArrayList<>();

			for (int i = 0; i < size; i++) {
				Node here = q.poll();
				nodeList.add(here.val);

				if (here.children != null) {
					for (Node child : here.children) {
						q.add(child);
					}
				}
			}

			res.add(nodeList);
		}

		return res;
	}
}
