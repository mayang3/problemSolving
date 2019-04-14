package hackerrank.cs.dataStructure.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class FindTheRunningMedian {

	static PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> -o1.compareTo(o2));
	static PriorityQueue<Integer> minHeap = new PriorityQueue<>();

	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//
//		int cnt = scanner.nextInt();
//
//		int [] vals = new int[cnt];
//
//		for (int i=0; i<cnt; i++) {
//			vals[i] = scanner.nextInt();
//		}

		int [] vals = {
			12, 4,5,3,8,7
		};

		int[] cp = Arrays.copyOf(vals, vals.length);

		Arrays.sort(cp);

		int midVal = cp[(cp.length-1) / 2];

		if (vals[0] < midVal) {
			maxHeap.add(vals[0]);
		} else {
			minHeap.add(vals[0]);
		}

		System.out.println((double)vals[0]);

		for (int i=1 ; i<vals.length ; i++) {
			if (vals[i] <= midVal) {
				maxHeap.add(vals[i]);
			} else {
				minHeap.add(vals[i]);
			}

			// rebalance
			if (maxHeap.size() > minHeap.size() + 1) {
			}

			if ((i+1)%2 == 0) {
				int lv = minHeap.isEmpty() ? 0 : minHeap.peek();
				int rv = maxHeap.isEmpty() ? 0 : maxHeap.peek();

				System.out.println(((double)lv+rv) / 2);

			} else {
				System.out.println((double)maxHeap.peek());
			}
		}
	}
}
