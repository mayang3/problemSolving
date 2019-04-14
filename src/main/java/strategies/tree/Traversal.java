package strategies.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baejunbeom
 */
public class Traversal {


	static class Node {
		Node left;
		Node right;
		int val;

		Node(Node left, Node right, int val) {
			this.left = left;
			this.right = right;
			this.val = val;
		}
	}

	// 트리의 전위탐색(preOrder) 결과와 중위탐색(inorder) 결과가 주어질때 후위탐색 (postorder) 결과를 출력한다.

	static void printPostOrder(List<Integer> preOrder, List<Integer> inOrder){
		// 트리에 포함된 노드의 수
		final int n = preOrder.size();

		// base case : empty 면 종료
		if (preOrder.isEmpty()) {
			return;
		}

		// 트리의 루트를 알아낸다.
		final int root = preOrder.get(0);
		// 트리의 왼쪽 서브트리의 크기를 알아낸다.
		final int left = inOrder.indexOf(root);
		// 오른쪽 서브트리의 크기는 N 에서 왼쪽 서브트리와 루트를 빼면 알 수 있다.
		final int right = n - 1 - left;

		// post order 의 순서인 left -> right -> root 순서대로 순회한다.

		// 왼쪽 서브트리 순회
		printPostOrder(preOrder.subList(1, left+1), inOrder.subList(0, left));
		// 오른쪽 서브트리 순회
		printPostOrder(preOrder.subList(left+1, n), inOrder.subList(left+1, n));

		System.out.println(root);
	}

	public static void main(String[] args) {
		List<Integer> preOrder = new ArrayList<>();
		preOrder.add(27);
		preOrder.add(16);
		preOrder.add(9);
		preOrder.add(12);
		preOrder.add(54);
		preOrder.add(36);
		preOrder.add(72);

		List<Integer> inOrder = new ArrayList<>();
		inOrder.add(9);
		inOrder.add(12);
		inOrder.add(16);
		inOrder.add(27);
		inOrder.add(36);
		inOrder.add(54);
		inOrder.add(72);

		printPostOrder(preOrder, inOrder);
	}

}
