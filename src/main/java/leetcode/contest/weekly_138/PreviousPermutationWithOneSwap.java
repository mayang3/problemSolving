package leetcode.contest.weekly_138;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author neo82
 */
public class PreviousPermutationWithOneSwap {
	public static void main(String[] args) {
		int [] A = {1,9,4,6,7};

		System.out.println(Arrays.toString(prevPermOpt1(A)));
	}

	static class Node {
		int i;
		int data;

		public Node(int i, int data) {
			this.i = i;
			this.data = data;
		}
	}

	public static int[] prevPermOpt1(int[] A) {
		swap(A);

		return A;
	}

	private static void swap(int[] A) {
		TreeSet<Node> treeSet = new TreeSet<>(Comparator.comparingInt(o -> o.data));

		for (int i = A.length-2; i >= 0; i--) {
			treeSet.add(new Node(i+1, A[i+1]));

			Node lower = treeSet.lower(new Node(i, A[i]));

			if (lower != null) {
				int temp = A[i];

				A[i] = lower.data;
				A[lower.i] = temp;

				return;
			}
		}
	}
}
