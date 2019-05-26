package codeforce.div3.round555;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class IncreasingSubsequence {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		Deque<Integer> ll = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			ll.add(scanner.nextInt());
		}

		StringBuilder sb = new StringBuilder();
		LinkedList<Integer> increasing = new LinkedList<>();

		while (ll.isEmpty() == false) {

			int first = ll.getFirst();
			int last = ll.getLast();

			boolean lCheck = isIncreasing(increasing, first);
			boolean rCheck = isIncreasing(increasing, last);

			if (!lCheck && !rCheck) {
				break;
			}

			if (lCheck && rCheck) {
				if (first == last) {
					increasing.add(ll.pollFirst());
					ll.pollLast();
					sb.append("R");
				} else if (first < last) {
					increasing.add(ll.pollFirst());
					sb.append("L");
				} else if (last < first) {
					increasing.add(ll.pollLast());
					sb.append("R");
				}
			} else if (lCheck) {
				increasing.add(ll.pollFirst());
				sb.append("L");
			} else if (rCheck) {
				increasing.add(ll.pollLast());
				sb.append("R");
			}

		}

		System.out.println(increasing.size());
		System.out.println(sb.toString());
	}


	static boolean isIncreasing(LinkedList<Integer> increasing, int num1) {
		if (increasing.isEmpty()) {
			return true;
		}

		if (increasing.getLast() < num1) {
			return true;
		}

		return false;
	}
}
