package strategies.tree;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author baejunbeom
 */
public class Treap {


	public static class Node {
		int key;
		int priority;
		// 이 노드를 루트로 하는 서브트리의 크기
		int size;
		// 두 자식 노드의 포인터
		Node left;
		Node right;

		Node(int key) {
			this.key = key;
			this.priority = randomPriority();
			this.size = 1;
		}

		void setLeft(Node left) {
			this.left = left;
			this.calcSize();
		}

		void setRight(Node right) {
			this.right = right;
			this.calcSize();
		}

		void calcSize() {
			this.size = 1;

			if (left != null) {
				this.size += left.size;
			}

			if (right != null) {
				this.size += right.size;
			}
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("Node{");
			sb.append("key=").append(key);
			sb.append(", priority=").append(priority);
			sb.append(", size=").append(size);
			sb.append(", left=").append(left);
			sb.append(", right=").append(right);
			sb.append('}');
			return sb.toString();
		}
	}

	static int randomPriority() {
		return ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
	}

	/*
		트립에서의 노드 추가와 트립 쪼개기 연산의 구현
	 */

	static class NodePair {
		Node first;
		Node second;

		NodePair(Node first, Node second) {
			this.first = first;
			this.second = second;
		}
	}

	// root 를 루트로 하는 트립을 key 미만의 값과 이상의 값을 갖는 두개의 트립으로 분리한다.
	NodePair split(Node root, int key) {
		if (root == null) return new NodePair(null, null);

		// 루트가 key 미만이면 오른쪽 서브트리를 쪼갠다.
		if (root.key < key) {
			NodePair rs = split(root.right, key);
			root.setRight(rs.first);
			return new NodePair(root, rs.second);
		}

		// 루트가 key 이상이면 왼쪽 서브트리를 쪼갠다.
		NodePair ls = split(root.left, key);
		root.setLeft(ls.second);
		return new NodePair(ls.first, root);
	}

	// root 를 루트로 하는 트립에 새 노드 node 를 삽입한 뒤 결과 트립의 루트를 반환한다.
	Node insert(Node root, Node node) {
		if (root == null) return node;

		// node 가 root 를 대체해야 한다. 해당 서브트리를 반으로 잘라 각각 자손으로 한다.
		if (root.priority < node.priority) {
			NodePair splitted = split(root, node.key);
			node.setLeft(splitted.first);
			node.setRight(splitted.second);
			return node;
		} else if (node.key < root.key) {
			// root 의 priority 가 더 크고, root 의 키값이 더 큰 경우
			root.setLeft(insert(root.left, node));
		} else {
			// root 의 priority 가 더 크고, root 의 키값이 더 작은 경우
			root.setRight(insert(root.right, node));
		}

		return root;
	}

	// 트립에서 노드의 삭제와 합치기 연산의 구현

	// a 와 b 가 두개의 트립이고, max(a) < max(b) 일 때, 이 둘을 합친다.
	Node merge(Node a, Node b) {
		if (a == null) return b;
		if (b == null) return a;

		// b 가 우선순위가 높을 경우에는, b가 root node 가 되고,
		if (a.priority < b.priority) {
			b.setLeft(merge(a, b.left));
			return b;
		}

		// a 가 우선순위가 높을 경우에는, a가 root node 가 된다.
		a.setRight(merge(a.right, b));
		return a;
	}

	// root 를 루트로하는 트립에서 key 를 지우고 결과 트립의 루트를 반환한다.
	Node erase(Node root, int key) {
		if (root == null) return root;

		// root 를 지우고 양 서브트리를 합친 뒤 반환한다.
		if (root.key == key) {
			Node ret = merge(root.left, root.right);

			deleteRoot(root);
			return ret;
		}

		if (key < root.key) {
			root.setLeft(erase(root.left, key));
		} else {
			root.setRight(erase(root.right, key));
		}

		return root;
	}

	private void deleteRoot(Node root) {
		root.left = null;
		root.right = null;
	}

	/*
		트립에서 k번째 원소를 찾는 알고리즘의 구현
	 */

	// root 를 루트로 하는 트리 중에서 k번째 원소를 반환한다.
	// kth 의 시간복잡도는 트리의 높이에 비례하기 때문에, 트립 등의 균형잡힌 이진 검색 트리에 적용해야만 O(logN) 시간에 수행할 수 있다.
	Node kth(Node root, int k) {
		// 왼쪽 서브트리의 크기를 우선 계산한다.
		int leftSize = 0;
		if (root.left != null) leftSize = root.left.size;

		// 만약 찾는 k 번째의 원소가 leftSize 보다 작다면, 반드시 왼쪽 트리에 포함되어 있다.
		if (k <= leftSize) return kth(root.left, k);
		// 만약 찾는 k 번째의 원소가 leftSize + 1 과 같다면, root 가 찾는 k 번째의 원소이다.
		if (k == leftSize + 1) return root;
		// 만약 찾는 k 번째의 원소가 leftSize + 1 보다 크다면, 반드시 오른쪽 트리에 포함되어 있다.
		return kth(root.right, k - leftSize - 1);
	}

	/*
		트립에서 x 보다 작은 원소의 수를 찾는 알고리즘의 구현
	 */

	// key 보다 작은 키값의 수를 반환한다.
	int countLessThan(Node root, int key) {
		if (root == null) return 0;

		// 루트의 key 가 찾고자 하는 key 와 같거나 큰 경우, 왼쪽의 subtree 를 재귀적으로 찾는다.
		if (root.key >= key) {
			return countLessThan(root.left, key);
		}

		// 루트의 key 가 찾고자 하는 key 보다 작은 경우, 오른쪽의 subtree 를 재귀적으로 찾고, root 의 left subtree 의 size를 더해준다.
		int ls = (root.left != null ? root.left.size : 0);

		// 여기서 1 은 root 의 개수이다.
		return ls + 1 + countLessThan(root.right, key);
	}

}
