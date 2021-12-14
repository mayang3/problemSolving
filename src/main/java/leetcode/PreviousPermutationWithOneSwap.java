package leetcode;

import java.util.*;

public class PreviousPermutationWithOneSwap {
	public int[] prevPermOpt1(int[] arr) {
		int start = findStart(arr);

		if (start == -1) {
			return arr;
		}

		swap(arr, start);

		return arr;
	}

	private void swap(int[] arr, int start) {
		PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.val == o2.val) {
				return o1.index - o2.index;
			}

			return o2.val - o1.val;
		});

		for (int i = start+1; i < arr.length; i++) {
			if (arr[i] >= arr[start]) {
				break;
			}
			pq.add(new Pair(i, arr[i]));
		}

		if (pq.size() > 0) {
			int end = pq.poll().index;

			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
		}
	}

	static class Pair {
		int index;
		int val;

		public Pair(int index, int val) {
			this.index = index;
			this.val = val;
		}
	}

	private int findStart(int[] arr) {
		TreeSet<Integer> treeSet = new TreeSet<>();

		for (int i = arr.length-1; i >= 0 ; i--) {
			Integer lower = treeSet.lower(arr[i]);

			if (lower != null) {
				return i;
			}

			treeSet.add(arr[i]);
		}

		return -1;
	}


	public static void main(String[] args) {
		int[] arr = {1,9,4,6,7};

		PreviousPermutationWithOneSwap previousPermutationWithOneSwap = new PreviousPermutationWithOneSwap();
		System.out.println(Arrays.toString(previousPermutationWithOneSwap.prevPermOpt1(arr)));
	}
}
