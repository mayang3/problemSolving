package baekjoon.tree;

import java.util.Scanner;

/**
 * AC
 */
@SuppressWarnings("ALL")
public class 트리의순회2 {
	static int [] inOrder;
	static int [] postOrder;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		inOrder = new int[n];

		for (int i=0 ; i<n ; i++) {
			inOrder[i] = scanner.nextInt();
		}

		postOrder = new int[n];

		for (int i=0 ; i<n ; i++) {
			postOrder[i] = scanner.nextInt();
		}

		preOrder(0, inOrder.length, 0, postOrder.length);

	}

	static void preOrder(int inOrder_l, int inOrder_r, int postOrder_l, int postOrder_r) {
		if (inOrder_l > inOrder_r-1 && postOrder_l > postOrder_r-1) {
			return;
		}

		int root = postOrder[postOrder_r - 1];

		System.out.print(root + " ");

		if (inOrder_l == inOrder_r-1 && postOrder_l == postOrder_r-1) {
			return;
		}

		int rootIndexInOrder=0;

		// find mid index
		for (int i = inOrder_l; i < inOrder_r; i++) {
			if (inOrder[i] == root) {
				rootIndexInOrder = i;
				break;
			}
		}

		/**
		 * rootIndexOrder 를 inOrder 배열에서 찾으므로 inOrder 의 범위는 rootIndexInOrder 를 기준으로 쉽게 자를 수 있지만..
		 *
		 * postOrder 는 inOrder 에서 잘린 길이를 계산해서 범위를 잘 지정해주어야 한다. 이 부분을 조심하자..
		 *
		 * postOrder 의 left 부분은 : postOrderLeft ~ postOrderLeft + inOrder 에서 자른 길이만큼이 된다.
		 *
		 * postOrder 의 right 부분은 : postOrderLeft + inOrder 에서 자른 길이부터 시작하되, root 를 찍어주느라 한개의 index 를 뺐기 때문에,
		 *
		 * 마지막에 postOrder_r 에 -1을 해준다.
		 *
		 */

		// left
		// inOrder : inOrder_l ~ rootIndexInOrder
		// postOrder : postOrder_l ~ postOrder_l + (inOrder 에서 자른 길이)
		preOrder(inOrder_l, rootIndexInOrder, postOrder_l, postOrder_l+(rootIndexInOrder-inOrder_l));

		// right
		//
		preOrder(rootIndexInOrder+1, inOrder_r, postOrder_l+(rootIndexInOrder-inOrder_l), postOrder_r-1);
	}

}
