package baekjoon.queue;

import java.util.*;

public class 조세퍼스0_1 {

	static List<Integer> solve(int N, int M) {
		Queue<Integer> queue = new LinkedList<>();

		for (int i=1 ; i<=N ; i++) {
			queue.add(i);
		}

		int count = 0;

		List<Integer> ret = new ArrayList<>();

		while (!queue.isEmpty()) {
			int poll = queue.poll();

			count++;

			if (count == M) {
				ret.add(poll);
				count = 0;
			} else {
				queue.add(poll);
			}
		}

		return ret;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int M = scanner.nextInt();

		System.out.println(solve(N, M).toString().replaceAll("\\[", "<").replaceAll("\\]", ">"));
	}

}
