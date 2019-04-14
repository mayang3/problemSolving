package hackerrank.cs.dataStructure.heap;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class QHeap {

	private static final PriorityQueue<Long> HEAP = new PriorityQueue<>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		long qCnt = scanner.nextLong();

		for (int i=0 ; i<qCnt ; i++) {
			long op = scanner.nextLong();

			if (op == 1) {
				addElement(scanner.nextLong());
			} else if (op == 2) {
				removeElement(scanner.nextLong());
			} else {
				printtElement();
			}
		}
	}

	private static void printtElement() {
		System.out.println(HEAP.peek());
	}

	private static void removeElement(long v) {
		HEAP.remove(v);
	}

	private static void addElement(long v) {
		HEAP.add(v);
	}
}
