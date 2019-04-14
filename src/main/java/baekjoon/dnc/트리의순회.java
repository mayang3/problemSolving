package baekjoon.dnc;

import java.util.Arrays;
import java.util.Scanner;

/**
 * {@link baekjoon.tree.트리의순회2}
 */
public class 트리의순회 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int [] inOrder = new int[n];

		for (int i=0 ; i<n ; i++) {
			inOrder[i] = scanner.nextInt();
		}

		int [] postOrder = new int[n];

		for (int i=0 ; i<n ; i++) {
			postOrder[i] = scanner.nextInt();
		}

		preOrder(inOrder, postOrder);

	}

	// 메모리 초과
	static void preOrder(int [] inOrder, int [] postOrder) {
		if (inOrder.length == 0 && postOrder.length == 0) {
			return;
		}

		int root = postOrder[postOrder.length - 1];

		System.out.print(root + " ");

		if (inOrder.length == 1 && postOrder.length == 1) {
			return;
		}

		int rootIndexInOrder=0;

		// find mid index
		while(rootIndexInOrder<inOrder.length) {
			if (inOrder[rootIndexInOrder] == root) {
				break;
			}

			rootIndexInOrder++;
		}

		// left
		preOrder(Arrays.copyOfRange(inOrder, 0, rootIndexInOrder), Arrays.copyOfRange(postOrder, 0, rootIndexInOrder));
		// right
		preOrder(Arrays.copyOfRange(inOrder, rootIndexInOrder+1, inOrder.length), Arrays.copyOfRange(postOrder, rootIndexInOrder, postOrder.length-1));
	}
}
