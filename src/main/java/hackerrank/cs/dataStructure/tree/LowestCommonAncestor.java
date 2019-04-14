package hackerrank.cs.dataStructure.tree;

/**
 * Accepted!
 *
 * https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor/problem
 *
 * @author baejunbeom
 */
public class LowestCommonAncestor {
	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}

	/**
	 * time complexity O(logN)
	 * space complexity O(1)
	 *
	 * @param root
	 * @param v1
	 * @param v2
	 * @return
	 */
	static Node lca(Node root, int v1, int v2) {
		// 1. base case : v1, v2 값이 root 의 left, right 로 분기하면 그 node 가 common ancestor 이다.
		// 2. base case : v1, v2 값 중 하나라도 root 값과 같다면 그 노드가 common ancestor 이다.
		if (v1 < root.data && v2 > root.data) {
			return root;
		} else if (v1 > root.data && v2 < root.data) {
			return root;
		} else if (v1 == root.data || v2 == root.data) {
			return root;
		}

		// 2. 값이 갈리지 않는다면 계속 찾는다.
		if (v1 < root.data && v2 < root.data) {
			return lca(root.left, v1, v2);
		}

		return lca(root.right, v1, v2);
	}

	static Node bs(Node root, int v) {

		if (root == null) {
			return null;
		}

		if (root.data > v) {
			return bs(root.left, v);
		} else if (root.data < v) {
			return bs(root.right, v);
		}

		return root;
	}

}
